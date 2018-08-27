package com.devops.competency.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "JOB_FREQUENCY")
public class JobFrequencyEntity {

	@EmbeddedId
	private JobFrequencyCompositeKey jobFrequencyCompositeKey;

	@Column(name = "JOB_NAME")
	private String jobName;

	@Column(name = "JOB_FREQUENCY_DATE")
	private String frequencyPerDay;

	public JobFrequencyCompositeKey getJobFrequencyCompositeKey() {
		return jobFrequencyCompositeKey;
	}

	public void setJobFrequencyCompositeKey(JobFrequencyCompositeKey jobFrequencyCompositeKey) {
		this.jobFrequencyCompositeKey = jobFrequencyCompositeKey;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getFrequencyPerDay() {
		return frequencyPerDay;
	}

	public void setFrequencyPerDay(String frequencyPerDay) {
		this.frequencyPerDay = frequencyPerDay;
	}

}
