package com.devops.competency.metadata;

public class MetaData extends AbstractDocument {

	private String metadataId;

	private String projectName;

	private String projectDescription;

	private JenkinsDetails jenkinDetails;

	private SonarDetails sonarDetails;

	private JiraDetails jiraDetails;

	public String getMetadataId() {
		return metadataId;
	}

	public void setMetadataId(String metadataId) {
		this.metadataId = metadataId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public JenkinsDetails getJenkinDetails() {
		return jenkinDetails;
	}

	public void setJenkinDetails(JenkinsDetails jenkinDetails) {
		this.jenkinDetails = jenkinDetails;
	}

	public JiraDetails getJiraDetails() {
		return jiraDetails;
	}

	public void setJiraDetails(JiraDetails jiraDetails) {
		this.jiraDetails = jiraDetails;
	}

	public SonarDetails getSonarDetails() {
		return sonarDetails;
	}

	public void setSonarDetails(SonarDetails sonarDetails) {
		this.sonarDetails = sonarDetails;
	}

}
