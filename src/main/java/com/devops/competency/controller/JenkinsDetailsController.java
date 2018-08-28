package com.devops.competency.controller;

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

	@GetMapping(value = "/jobFrequency/{jobId}")
	public ResponseEntity<List<JobFrequency>> getJobFrequencyDetailsForJobId(
			@PathVariable(value = "jobId") String jobId) {
		List<JobFrequency> jobFrequencyList = jobFrequencyRepository.getJobDetails(jobId);

		if (jobFrequencyList.isEmpty()) {
			return new ResponseEntity<List<JobFrequency>>(jobFrequencyList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<JobFrequency>>(jobFrequencyList, HttpStatus.OK);
	}

}
