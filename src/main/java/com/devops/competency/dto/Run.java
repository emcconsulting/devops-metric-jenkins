package com.devops.competency.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "_links", "id", "name", "status", "startTimeMillis", "endTimeMillis", "durationMillis",
		"queueDurationMillis", "pauseDurationMillis", "stages" })
public class Run {

	@JsonProperty("_links")
	private Links_ links;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("status")
	private String status;
	@JsonProperty("startTimeMillis")
	private Long startTimeMillis;
	@JsonProperty("endTimeMillis")
	private Long endTimeMillis;
	@JsonProperty("durationMillis")
	private Long durationMillis;
	@JsonProperty("queueDurationMillis")
	private Long queueDurationMillis;
	@JsonProperty("pauseDurationMillis")
	private Long pauseDurationMillis;
	@JsonProperty("stages")
	private List<Stage> stages = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("_links")
	public Links_ getLinks() {
		return links;
	}

	@JsonProperty("_links")
	public void setLinks(Links_ links) {
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

	@JsonProperty("endTimeMillis")
	public Long getEndTimeMillis() {
		return endTimeMillis;
	}

	@JsonProperty("endTimeMillis")
	public void setEndTimeMillis(Long endTimeMillis) {
		this.endTimeMillis = endTimeMillis;
	}

	@JsonProperty("durationMillis")
	public Long getDurationMillis() {
		return durationMillis;
	}

	@JsonProperty("durationMillis")
	public void setDurationMillis(Long durationMillis) {
		this.durationMillis = durationMillis;
	}

	@JsonProperty("queueDurationMillis")
	public Long getQueueDurationMillis() {
		return queueDurationMillis;
	}

	@JsonProperty("queueDurationMillis")
	public void setQueueDurationMillis(Long queueDurationMillis) {
		this.queueDurationMillis = queueDurationMillis;
	}

	@JsonProperty("pauseDurationMillis")
	public Long getPauseDurationMillis() {
		return pauseDurationMillis;
	}

	@JsonProperty("pauseDurationMillis")
	public void setPauseDurationMillis(Long pauseDurationMillis) {
		this.pauseDurationMillis = pauseDurationMillis;
	}

	@JsonProperty("stages")
	public List<Stage> getStages() {
		return stages;
	}

	@JsonProperty("stages")
	public void setStages(List<Stage> stages) {
		this.stages = stages;
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