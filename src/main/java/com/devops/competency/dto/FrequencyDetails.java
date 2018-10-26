package com.devops.competency.dto;

public class FrequencyDetails {

	private String projectName;
	private String frequency;
	private Double frequencyPerYear;

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Double getFrequencyPerYear() {
		return frequencyPerYear;
	}

	public void setFrequencyPerYear(Double frequencyPerYear) {
		this.frequencyPerYear = frequencyPerYear;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
