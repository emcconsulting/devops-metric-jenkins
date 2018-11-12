package com.devops.competency.dto;

public class FrequencyDetails {

	private String projectName;
	private String frequency;
	private Double frequencyPerWeek;

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Double getFrequencyPerWeek() {
		return frequencyPerWeek;
	}

	public void setFrequencyPerWeek(Double frequencyPerWeek) {
		this.frequencyPerWeek = frequencyPerWeek;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
