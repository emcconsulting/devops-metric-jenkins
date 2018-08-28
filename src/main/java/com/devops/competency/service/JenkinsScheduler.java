package com.devops.competency.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.devops.competency.dao.JenkinsStageDetailsRepository;
import com.devops.competency.dao.JobFrequencyRepository;
import com.devops.competency.dao.JobMetaDataRepository;
import com.devops.competency.dto.Run;
import com.devops.competency.dto.Stage;
import com.devops.competency.entity.JenkinsCompoiteKey;
import com.devops.competency.entity.JenkinsStageDetails;
import com.devops.competency.entity.JobFrequencyCompositeKey;
import com.devops.competency.entity.JobFrequency;
import com.devops.competency.entity.JobMetaData;

@Component
public class JenkinsScheduler {

	private static final String SUCCESS = "Success";

	public static final Logger logger = LoggerFactory.getLogger(JenkinsScheduler.class);

	@Autowired
	CompetencyServiceImpl competencyServiceImpl;

	@Autowired
	JobMetaDataRepository jobMetaDataRepository;

	@Autowired
	JenkinsStageDetailsRepository jenkinsStageDetailsRepo;

	@Autowired
	JobFrequencyRepository jobFrequencyRepository;

	@Scheduled(cron = "${api.refresh.pulljenkinsdetail.cron}")
	private void pullJenkinsDetails() {
		logger.info("---Job started pullJenkinsDetails----");

		jobMetaDataRepository.findAll().forEach(jobMetadata -> {
			logger.info(" Found data for :: JobMEtadata {} ", jobMetadata);
			if (null != jobMetadata)
				runJob(jobMetadata);
		});
		logger.info("---Job Ended pullJenkinsDetails----");
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Scheduled(cron = "${api.refresh.frequency.cron}") private void
	 * frequencyScheduler() { logger.info("---Job started frequencyScheduler----");
	 * 
	 * jenkinsStageDetailsRepo.getJobFrequency().forEach(jobfrequency -> { Object[]
	 * ss = (Object[]) jobfrequency; JobFrequencyEntity frequencyEntity = new
	 * JobFrequencyEntity(); JobFrequencyCompositeKey jobFrequencyCompositeKey = new
	 * JobFrequencyCompositeKey();
	 * jobFrequencyCompositeKey.setJobId(ss[0].toString());
	 * jobFrequencyCompositeKey.setJobDate((Date) (ss[3]));
	 * frequencyEntity.setJobFrequencyCompositeKey(jobFrequencyCompositeKey);
	 * frequencyEntity.setFrequencyPerDay(ss[2].toString());
	 * frequencyEntity.setJobName(ss[1].toString());
	 * jobFrequencyRepository.save(frequencyEntity); });
	 * 
	 * logger.info("---Job Ended frequencyScheduler----"); }
	 */

	@SuppressWarnings("unchecked")
	private void runJob(JobMetaData jobMetadata) {
		Run[] allRuns = competencyServiceImpl.getAllRuns(jobMetadata.getJobName());
		if (null != allRuns) {
			List<Run> runList = new ArrayList<Run>(Arrays.asList(allRuns));
			Calendar today = Calendar.getInstance();
			logger.info("Scheduler is running for date :: {} ", today.getTime());
			// today.add(Calendar.DATE, -1);

			// filter the run happened today
			Stream<Run> runstream = runList.stream().filter(run -> {
				Calendar jobRunDate = Calendar.getInstance();
				jobRunDate.setTime(new Date(run.getStartTimeMillis()));
				if (isSameDay(today, jobRunDate)) {
					return true;
				} else {
					return false;
				}
			});

			runstream.forEach(run -> {
				run.getStages().forEach(stage -> {
					if (stage.getName().equalsIgnoreCase(jobMetadata.getDeployableStageName())
							&& stage.getStatus().equalsIgnoreCase(SUCCESS)) {

						JenkinsStageDetails jenkinsStageDetails = constructJenkinsStageDetailsEntity(run, stage,
								jobMetadata);

						logger.debug("VALUES :: {} ", jenkinsStageDetails);
						if (jenkinsStageDetailsRepo
								.findOne(getCompositeKey(run, jobMetadata, new JenkinsCompoiteKey())) == null) {
							jenkinsStageDetailsRepo.save(jenkinsStageDetails);
						}

						/*
						 * // save this values in to DB System.out.println(jobMetadata);
						 * System.out.println(run.getName()); System.out.println(new
						 * Date(run.getStartTimeMillis())); System.out.println(run.getStatus());
						 * System.out.println(stage.getStatus());
						 */
					}
				});
			});

			jenkinsStageDetailsRepo.getJobFrequency(today.getTime()).forEach(jobfrequency -> {
				Object[] ss = (Object[]) jobfrequency;
				JobFrequency frequencyEntity = new JobFrequency();
				JobFrequencyCompositeKey jobFrequencyCompositeKey = new JobFrequencyCompositeKey();
				jobFrequencyCompositeKey.setJobId(ss[0].toString());
				jobFrequencyCompositeKey.setJobDate((Date) (ss[3]));
				frequencyEntity.setJobFrequencyCompositeKey(jobFrequencyCompositeKey);
				frequencyEntity.setFrequencyPerDay(ss[2].toString());
				frequencyEntity.setJobName(ss[1].toString());
				jobFrequencyRepository.save(frequencyEntity);
			});
		}
	}

	private JenkinsStageDetails constructJenkinsStageDetailsEntity(Run run, Stage stage, JobMetaData jobMetadata) {
		JenkinsStageDetails details = new JenkinsStageDetails();

		JenkinsCompoiteKey compoiteKey = new JenkinsCompoiteKey();
		getCompositeKey(run, jobMetadata, compoiteKey);

		details.setJobName(jobMetadata.getJobName());
		details.setJenkinsCompoiteKey(compoiteKey);
		details.setJobRunDate(new Date(run.getStartTimeMillis()));
		details.setStageName(stage.getName());
		details.setStageStatus(stage.getStatus());
		details.setJobRunStatus(run.getStatus());
		return details;
	}

	private JenkinsCompoiteKey getCompositeKey(Run run, JobMetaData jobMetadata, JenkinsCompoiteKey compoiteKey) {
		compoiteKey.setJobInstanceName(run.getName());
		compoiteKey.setJobId(jobMetadata.getJobName());
		return compoiteKey;
	}

	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}
}
