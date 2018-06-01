package com.devops.competency.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "_links", "name", "runCount" })
public class Project {

//	public Project(Links links, String name, Integer runCount, Map<String, Object> additionalProperties) {
//		super();
//		this.links = links;
//		this.name = name;
//		this.runCount = runCount;
//		
//	}

	@JsonProperty("_links")
	private Links links;
	@JsonProperty("name")
	private String name;
	@JsonProperty("runCount")
	private Integer runCount;
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

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("runCount")
	public Integer getRunCount() {
		return runCount;
	}

	@JsonProperty("runCount")
	public void setRunCount(Integer runCount) {
		this.runCount = runCount;
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