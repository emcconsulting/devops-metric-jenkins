package com.devops.competency.dao;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "metric", "op", "warning", "error", "period" })
public class Rule {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("metric")
	private String metric;

	public Rule() {
		
	}
	public Rule(String metric, String op, String warning, String error, Integer period) {

		this.metric = metric;
		this.op = op;
		this.warning = warning;
		this.error = error;
		this.period = period;

	}

	@JsonProperty("op")
	private String op;
	@JsonProperty("warning")
	private String warning;
	@JsonProperty("error")
	private String error;
	@JsonProperty("period")
	private Integer period;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("metric")
	public String getMetric() {
		return metric;
	}

	@JsonProperty("metric")
	public void setMetric(String metric) {
		this.metric = metric;
	}

	@JsonProperty("op")
	public String getOp() {
		return op;
	}

	@JsonProperty("op")
	public void setOp(String op) {
		this.op = op;
	}

	@JsonProperty("warning")
	public String getWarning() {
		return warning;
	}

	@JsonProperty("warning")
	public void setWarning(String warning) {
		this.warning = warning;
	}

	@JsonProperty("error")
	public String getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(String error) {
		this.error = error;
	}

	@JsonProperty("period")
	public Integer getPeriod() {
		return period;
	}

	@JsonProperty("period")
	public void setPeriod(Integer period) {
		this.period = period;
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