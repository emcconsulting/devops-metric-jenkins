package com.devops.competency.dto;

public class Rule {
	
	String path;
	String predicateKey;
	public Rule(String path, String predicateKey) {
		super();
		this.path = path;
		this.predicateKey = predicateKey;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPredicateKey() {
		return predicateKey;
	}
	public void setPredicateKey(String predicateKey) {
		this.predicateKey = predicateKey;
	}

}
