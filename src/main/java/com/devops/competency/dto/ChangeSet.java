package com.devops.competency.dto;

import java.util.HashMap;
import java.util.List;
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
@JsonPropertyOrder({ "kind", "commitCount", "commits", "consoleUrl" })
public class ChangeSet {

	@JsonProperty("kind")
	private String kind;
	@JsonProperty("commitCount")
	private Integer commitCount;
	@JsonProperty("commits")
	private List<Commit> commits = null;
	@JsonProperty("consoleUrl")
	private String consoleUrl;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("kind")
	public String getKind() {
		return kind;
	}

	@JsonProperty("kind")
	public void setKind(String kind) {
		this.kind = kind;
	}

	@JsonProperty("commitCount")
	public Integer getCommitCount() {
		return commitCount;
	}

	@JsonProperty("commitCount")
	public void setCommitCount(Integer commitCount) {
		this.commitCount = commitCount;
	}

	@JsonProperty("commits")
	public List<Commit> getCommits() {
		return commits;
	}

	@JsonProperty("commits")
	public void setCommits(List<Commit> commits) {
		this.commits = commits;
	}

	@JsonProperty("consoleUrl")
	public String getConsoleUrl() {
		return consoleUrl;
	}

	@JsonProperty("consoleUrl")
	public void setConsoleUrl(String consoleUrl) {
		this.consoleUrl = consoleUrl;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}