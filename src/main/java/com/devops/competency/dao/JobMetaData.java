package com.devops.competency.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "JOB_META_DATA")
public class JobMetaData {

	@Id
	private String jobName;

	@Column(name = "DEPLOY_STAGE_NAME")
	private String deployableStageName;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDeployableStageName() {
		return deployableStageName;
	}

	public void setDeployableStageName(String deployableStageName) {
		this.deployableStageName = deployableStageName;
	}

	@Override
	public String toString() {
		return "JobMetaData [jobName=" + jobName + ", deployableStageName=" + deployableStageName + "]";
	}

}
