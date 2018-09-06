package com.devops.competency.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JenkinsCompoiteKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "JOB_ID")
	private String jobId;

	@Column(name = "JOB_INSTANCE")
	private String jobInstanceName;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobInstanceName() {
		return jobInstanceName;
	}

	public void setJobInstanceName(String jobInstanceName) {
		this.jobInstanceName = jobInstanceName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result + ((jobInstanceName == null) ? 0 : jobInstanceName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JenkinsCompoiteKey other = (JenkinsCompoiteKey) obj;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (jobInstanceName == null) {
			if (other.jobInstanceName != null)
				return false;
		} else if (!jobInstanceName.equals(other.jobInstanceName))
			return false;
		return true;
	}

}
