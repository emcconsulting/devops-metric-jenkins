package com.devops.competency.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.devops.competency.dao.JobFrequencyRepository;
import com.devops.competency.entity.JobFrequency;

@RestController
public class JenkinsDetailsController {

	@Autowired
	JobFrequencyRepository jobFrequencyRepository;

	@GetMapping(value = "/jobFrequency/all")
	public ResponseEntity<List<JobFrequency>> getJobFrequencyDetails() {
		return new ResponseEntity<List<JobFrequency>>(jobFrequencyRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/jobFrequency/perday/{jobId}")
	public ResponseEntity<List<JobFrequency>> getJobFrequencyDetailsPerdayForJobId(
			@PathVariable(value = "jobId") String jobId) {
		List<JobFrequency> jobFrequencyList = jobFrequencyRepository.getJobDetails(jobId);

		if (jobFrequencyList.isEmpty()) {
			return new ResponseEntity<List<JobFrequency>>(jobFrequencyList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<JobFrequency>>(jobFrequencyList, HttpStatus.OK);
	}

	@GetMapping(value = "/jobFrequency/{jobId}")
	public String getJobFrequencyDetailsForJobId(@PathVariable(value = "jobId") String jobId) {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.YEAR, -1);
		Date oneYearOldDate = today.getTime();
		Integer deployedValueForYear = jobFrequencyRepository.getJobDetailsForYears(jobId, oneYearOldDate);
		if (deployedValueForYear < 12)
			return "deployed " + deployedValueForYear + " times per year";
		else if (deployedValueForYear >= 12 && deployedValueForYear < 52)
			return "deployed " + deployedValueForYear / 12 + " times per month";
		else if (deployedValueForYear >= 52 && deployedValueForYear < 365)
			return "deployed " + deployedValueForYear / 52 + " times per week";
		else
			return "deployed " + deployedValueForYear / 365 + " times per day";
	}

}
