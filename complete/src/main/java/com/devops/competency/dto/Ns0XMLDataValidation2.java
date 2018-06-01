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
@JsonPropertyOrder({ "-xmlns:ns0", "ns0:DataElement" })
public class Ns0XMLDataValidation2 {

	@JsonProperty("-xmlns:ns0")
	private String xmlnsNs0;
	@JsonProperty("ns0:DataElement")
	private Ns0DataElement ns0DataElement;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("-xmlns:ns0")
	public String getXmlnsNs0() {
		return xmlnsNs0;
	}

	@JsonProperty("-xmlns:ns0")
	public void setXmlnsNs0(String xmlnsNs0) {
		this.xmlnsNs0 = xmlnsNs0;
	}

	@JsonProperty("ns0:DataElement")
	public Ns0DataElement getNs0DataElement() {
		return ns0DataElement;
	}

	@JsonProperty("ns0:DataElement")
	public void setNs0DataElement(Ns0DataElement ns0DataElement) {
		this.ns0DataElement = ns0DataElement;
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