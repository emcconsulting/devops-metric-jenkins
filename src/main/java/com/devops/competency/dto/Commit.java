package com.devops.competency.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "commitId", "commitUrl", "authorJenkinsId", "message", "consoleUrl" })
public class Commit {

	public Commit(String commitId, String commitUrl, String authorJenkinsId, String message, String consoleUrl, Error child) {
		this.commitId = commitId;
		this.commitUrl = commitUrl;
		this.authorJenkinsId = authorJenkinsId;
		this.message = message;
		this.consoleUrl = consoleUrl;
		this.child= child;
	}
	
	public Commit() {
		
	}

	@JsonProperty("commitId")
	private String commitId;
	@JsonProperty("commitUrl")
	private String commitUrl;
	@JsonProperty("child")
	public Error getChild() {
		return child;
	}
	@JsonProperty("child")
	public void setChild(Error child) {
		this.child = child;
	}

	@JsonProperty("authorJenkinsId")
	private String authorJenkinsId;
	@JsonProperty("message")
	private String message;
	@JsonProperty("consoleUrl")
	private String consoleUrl;
	@JsonProperty("child")
	private Error child;
	

	@JsonProperty("commitId")
	public String getCommitId() {
		return commitId;
	}

	@JsonProperty("commitId")
	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	@JsonProperty("commitUrl")
	public String getCommitUrl() {
		return commitUrl;
	}

	@JsonProperty("commitUrl")
	public void setCommitUrl(String commitUrl) {
		this.commitUrl = commitUrl;
	}

	@JsonProperty("authorJenkinsId")
	public String getAuthorJenkinsId() {
		return authorJenkinsId;
	}

	@JsonProperty("authorJenkinsId")
	public void setAuthorJenkinsId(String authorJenkinsId) {
		this.authorJenkinsId = authorJenkinsId;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}


	@JsonProperty("consoleUrl")
	public String getConsoleUrl() {
		return consoleUrl;
	}

	@JsonProperty("consoleUrl")
	public void setConsoleUrl(String consoleUrl) {
		this.consoleUrl = consoleUrl;
	}
	


}