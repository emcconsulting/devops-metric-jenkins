package com.devops.competency.controller;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	  @ExceptionHandler(value = { Exception.class })
	    protected ResponseEntity<ErrorCompetency> handleConflict(RuntimeException ex, WebRequest request) {
	        String bodyOfResponse = "This should be application specific";
	        return handleExceptionInternal(ex, bodyOfResponse, 
	          new HttpHeaders(), HttpStatus.CONFLICT, request);
	    }

	  private ResponseEntity<ErrorCompetency> handleExceptionInternal(RuntimeException ex, String bodyOfResponse,
			  HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest request) {
		  final String message = Optional.of(ex.getMessage()).orElse(ex.getClass().getSimpleName());
		  return new ResponseEntity<ErrorCompetency> (new ErrorCompetency(message),httpStatus);

	  	}
}
