package com.devops.competency.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Projects {

	@JsonProperty("jobs")
	private List<Job> projects = null;

	public List<Job> getJobs() {
		return projects;
	}

	public void setJobs(List<Job> projects) {
		this.projects = projects;
	}
}
