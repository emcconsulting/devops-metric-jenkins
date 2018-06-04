package com.devops.competency.controller;

import org.springframework.http.HttpStatus;

public class ErrorCompetency {
	
	String messag;
	HttpStatus httpsStatus;
	public String getMessag() {
		return messag;
	}
	public void setMessag(String messag) {
		this.messag = messag;
	}
	public HttpStatus getHttpsStatus() {
		return httpsStatus;
	}
	public void setHttpsStatus(HttpStatus httpsStatus) {
		this.httpsStatus = httpsStatus;
	}
	public ErrorCompetency(String messag) {
		super();
		this.messag = messag;
		this.httpsStatus = httpsStatus;
	}
	

}
