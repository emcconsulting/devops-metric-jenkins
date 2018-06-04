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
@JsonPropertyOrder({ "self", "changesets" })
public class Links_ {

	@JsonProperty("self")
	private Self self;
	@JsonProperty("changesets")
	private ChangeSet changesets;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("self")
	public Self getSelf() {
		return self;
	}

	@JsonProperty("self")
	public void setSelf(Self self) {
		this.self = self;
	}

	@JsonProperty("changesets")
	public ChangeSet getChangesets() {
		return changesets;
	}

	@JsonProperty("changesets")
	public void setChangesets(ChangeSet changesets) {
		this.changesets = changesets;
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