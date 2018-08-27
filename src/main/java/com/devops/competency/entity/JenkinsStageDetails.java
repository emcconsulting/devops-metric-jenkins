package com.devops.competency.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "JENKINS_JOB_DETAILS")
public class JenkinsStageDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	JenkinsCompoiteKey jenkinsCompoiteKey;

	@Column(name = "JOB_STATUS")
	private String jobRunStatus;

	@Column(name = "STAGE_NAME")
	private String stageName;

	@Column(name = "STAGE_STATUS")
	private String stageStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "JOB_RUN_DATE")
	private Date jobRunDate;

	public JenkinsCompoiteKey getJenkinsCompoiteKey() {
		return jenkinsCompoiteKey;
	}

	public void setJenkinsCompoiteKey(JenkinsCompoiteKey jenkinsCompoiteKey) {
		this.jenkinsCompoiteKey = jenkinsCompoiteKey;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getStageStatus() {
		return stageStatus;
	}

	public void setStageStatus(String stageStatus) {
		this.stageStatus = stageStatus;
	}

	public String getJobRunStatus() {
		return jobRunStatus;
	}

	public void setJobRunStatus(String jobRunStatus) {
		this.jobRunStatus = jobRunStatus;
	}

	public Date getJobRunDate() {
		return jobRunDate;
	}

	public void setJobRunDate(Date jobRunDate) {
		this.jobRunDate = jobRunDate;
	}

	@Override
	public String toString() {
		return "JenkinsStageDetails [jenkinsCompoiteKey=" + jenkinsCompoiteKey + ", jobRunStatus=" + jobRunStatus
				+ ", stageName=" + stageName + ", stageStatus=" + stageStatus + ", jobRunDate=" + jobRunDate + "]";
	}

}
