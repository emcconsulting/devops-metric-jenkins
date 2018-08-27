package com.devops.competency.controller;

import org.springframework.http.HttpStatus;

public class ErrorCompetency {
	
	String message;
	HttpStatus httpsStatus;
	public String getMessag() {
		return message;
	}
	public void setMessag(String message) {
		this.message = message;
	}
	public HttpStatus getHttpsStatus() {
		return httpsStatus;
	}
	public void setHttpsStatus(HttpStatus httpsStatus) {
		this.httpsStatus = httpsStatus;
	}
	public ErrorCompetency(String message) {
		super();
		this.message = message;
		this.httpsStatus = httpsStatus;
	}
	

}
