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

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "self", "runs" })
public class Links {
//
//	public Links(Self self, Runs runs) {
//		super();
//		this.self = self;
//		this.runs = runs;
//		
//	}

	@JsonProperty("self")
	private Self self;
	@JsonProperty("runs")
	private Runs runs;
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

	@JsonProperty("runs")
	public Runs getRuns() {
		return runs;
	}

	@JsonProperty("runs")
	public void setRuns(Runs runs) {
		this.runs = runs;
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