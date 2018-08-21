package com.devops.competency.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JenkinsCompoiteKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "JOB_INSTANCE")
	private String jobInstanceName;

	@Column(name = "JOB_NAME")
	private String jobName;

	public String getJobInstanceName() {
		return jobInstanceName;
	}

	public void setJobInstanceName(String jobInstanceName) {
		this.jobInstanceName = jobInstanceName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobInstanceName == null) ? 0 : jobInstanceName.hashCode());
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
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
		if (jobInstanceName == null) {
			if (other.jobInstanceName != null)
				return false;
		} else if (!jobInstanceName.equals(other.jobInstanceName))
			return false;
		if (jobName == null) {
			if (other.jobName != null)
				return false;
		} else if (!jobName.equals(other.jobName))
			return false;
		return true;
	}

}
