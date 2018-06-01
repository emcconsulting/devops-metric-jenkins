package com.devops.competency.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubCommits {
	
	private Commit commits[]= null;

	public Commit[] getCommits() {
		return commits;
	}

	public void setCommits(Commit[] commits) {
		this.commits = commits;
	}
	
	public ClubCommits(Commit[] commit) {
		
	};
	
	public ClubCommits() {
		
	}

}
