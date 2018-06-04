package com.devops.competency.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.devops.competency.dto.Error;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "_links", "id", "name", "execNode", "status", "startTimeMillis", "durationMillis",
		"pauseDurationMillis", "error" })
public class Stage {

	@JsonProperty("_links")
	private Links links;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("execNode")
	private String execNode;
	@JsonProperty("status")
	private String status;
	@JsonProperty("startTimeMillis")
	private Long startTimeMillis;
	@JsonProperty("durationMillis")
	private Long durationMillis;
	@JsonProperty("pauseDurationMillis")
	private Long pauseDurationMillis;
	@JsonProperty("error")
	private Error error;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("_links")
	public Links getLinks() {
		return links;
	}

	@JsonProperty("_links")
	public void setLinks(Links links) {
		this.links = links;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("execNode")
	public String getExecNode() {
		return execNode;
	}

	@JsonProperty("execNode")
	public void setExecNode(String execNode) {
		this.execNode = execNode;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("startTimeMillis")
	public Long getStartTimeMillis() {
		return startTimeMillis;
	}

	@JsonProperty("startTimeMillis")
	public void setStartTimeMillis(Long startTimeMillis) {
		this.startTimeMillis = startTimeMillis;
	}

	@JsonProperty("durationMillis")
	public Long getDurationMillis() {
		return durationMillis;
	}

	@JsonProperty("durationMillis")
	public void setDurationMillis(Long durationMillis) {
		this.durationMillis = durationMillis;
	}

	@JsonProperty("pauseDurationMillis")
	public Long getPauseDurationMillis() {
		return pauseDurationMillis;
	}

	@JsonProperty("pauseDurationMillis")
	public void setPauseDurationMillis(Long pauseDurationMillis) {
		this.pauseDurationMillis = pauseDurationMillis;
	}

	@JsonProperty("error")
	public Error getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(Error error) {
		this.error = error;
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