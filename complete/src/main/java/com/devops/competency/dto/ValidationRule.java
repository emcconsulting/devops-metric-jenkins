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
@JsonPropertyOrder({ "ns0:XMLDataValidation" })
public class ValidationRule {

	@JsonProperty("ns0:XMLDataValidation")
	private Ns0XMLDataValidation ns0XMLDataValidation;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("ns0:XMLDataValidation")
	public Ns0XMLDataValidation getNs0XMLDataValidation() {
		return ns0XMLDataValidation;
	}

	@JsonProperty("ns0:XMLDataValidation")
	public void setNs0XMLDataValidation(Ns0XMLDataValidation ns0XMLDataValidation) {
		this.ns0XMLDataValidation = ns0XMLDataValidation;
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