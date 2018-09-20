package com.devops.competency.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.devops.competency.dao.JobFrequencyRepository;
import com.devops.competency.entity.JobFrequency;
import com.devops.competency.utils.MetaDataClient;

@RestController
public class JenkinsDetailsController {

	@Autowired
	JobFrequencyRepository jobFrequencyRepository;

	@Autowired
	MetaDataClient metaDataClient;

	/*@GetMapping(value = "/jobFrequency/all")
	public ResponseEntity<List<JobFrequency>> getJobFrequencyDetails() {
		return new ResponseEntity<List<JobFrequency>>(jobFrequencyRepository.findAll(), HttpStatus.OK);
	}*/

	@GetMapping(value = "/jenkinks/jobfrequency/perday/{jobId}")
	public ResponseEntity<List<JobFrequency>> getJobFrequencyDetailsPerdayForJobId(
			@PathVariable(value = "jobId") String jobId) {
		List<JobFrequency> jobFrequencyList = jobFrequencyRepository.getJobDetails(jobId);

		if (jobFrequencyList.isEmpty()) {
			return new ResponseEntity<List<JobFrequency>>(jobFrequencyList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<JobFrequency>>(jobFrequencyList, HttpStatus.OK);
	}

	@GetMapping(value = "/jenkins/jobFrequency/{jobId}")
	public String getJobFrequencyDetailsForJobId(@PathVariable(value = "jobId") String jobId) {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.YEAR, -1);
		Date oneYearOldDate = today.getTime();
		Integer deployedValueForYear = jobFrequencyRepository.getJobDetailsForYears(jobId, oneYearOldDate);
		return getFrequencyString(deployedValueForYear);

	}

	@GetMapping(value = "/jenkins/jobfrequency/all")
	public Map<String, String> getJobFrequencyDetailsForAll() {
		Map<String, String> map = new HashMap<String, String>();
		Calendar today = Calendar.getInstance();
		today.add(Calendar.YEAR, -1);
		Date oneYearOldDate = today.getTime();
		List<Object[]> deployedValueForYear = jobFrequencyRepository.getJobDetailsForAll(oneYearOldDate);
		for (Object[] aa : deployedValueForYear) {
			map.put((String) aa[0], getFrequencyString(Integer.parseInt((String) aa[1])));
		}
		return map;
	}

	private String getFrequencyString(Integer deployedValueForYear) {
		if (deployedValueForYear < 12)
			return "deployed " + deployedValueForYear + " times per year" + " ~"
					+ (double) (deployedValueForYear / 365);
		else if (deployedValueForYear >= 12 && deployedValueForYear < 52)
			return "deployed " + deployedValueForYear / 12 + " times per month" + " ~"
					+ (double) (deployedValueForYear / 365);
		else if (deployedValueForYear >= 52 && deployedValueForYear < 365)
			return "deployed " + deployedValueForYear / 52 + " times per week" + " ~"
					+ (double) (deployedValueForYear / 365);
		else
			return "deployed " + deployedValueForYear / 365 + " times per day" + " ~"
					+ (double) (deployedValueForYear / 365);
	}

	@GetMapping(value = "/test")
	public void test() {
		System.out.println(metaDataClient.getMetadata());
	}
}
