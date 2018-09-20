package com.devops.competency.metadata;

public class JenkinsDetails {

	private String jenkinsJobName;
	private String deployableStageName;
	private String jenkinsUrl;
	private String jenkinProjectName;
	private String jenkinsUsername;
	private String jenkinsPassword;

	public String getJenkinsUrl() {
		return jenkinsUrl;
	}

	public void setJenkinsUrl(String jenkinsUrl) {
		this.jenkinsUrl = jenkinsUrl;
	}

	public String getJenkinProjectName() {
		return jenkinProjectName;
	}

	public void setJenkinProjectName(String jenkinProjectName) {
		this.jenkinProjectName = jenkinProjectName;
	}

	public String getJenkinsUsername() {
		return jenkinsUsername;
	}

	public void setJenkinsUsername(String jenkinsUsername) {
		this.jenkinsUsername = jenkinsUsername;
	}

	public String getJenkinsPassword() {
		return jenkinsPassword;
	}

	public void setJenkinsPassword(String jenkinsPassword) {
		this.jenkinsPassword = jenkinsPassword;
	}

	public String getJenkinsJobName() {
		return jenkinsJobName;
	}

	public void setJenkinsJobName(String jenkinsJobName) {
		this.jenkinsJobName = jenkinsJobName;
	}

	public String getDeployableStageName() {
		return deployableStageName;
	}

	public void setDeployableStageName(String deployableStageName) {
		this.deployableStageName = deployableStageName;
	}

}
