package com.devops.competency.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import com.devops.competency.dto.ChangeSet;
import com.devops.competency.dto.Project;
import com.devops.competency.dto.Projects;
import com.devops.competency.dto.Run;

@Service
public class CompetencyServiceImpl {
	
	public static final String JENKINSURL="http://jenkins.ci-server.com";
	public static final String SONARURL="http://sonar.ci-server.com";
//	CompetencyServiceImpl competencyServiceImpl= new CompetencyServiceImpl();
	public Project getProjectDetails() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));
		Project result = restTemplate.getForObject(JENKINSURL+"/job/"+"/wfapi/",
				Project.class);
		System.out.println(result);
		return result;
		// return new Project(new Links(new Self("https://abc"), new
		// Runs("https://abc/describe")), "CICD Competancy", null, null);
	}

	public Projects getProject() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));
		Projects result = restTemplate.getForObject(JENKINSURL+"/api/json", Projects.class);
		return result;
	}
	
	public Run[] getAllRuns() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));		
		Run run []= restTemplate.getForObject(JENKINSURL+"/job/mdthelloworld/wfapi/runs", Run[].class);	
		return run;
		
	}
	
	public Object getStageDetails(String path) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));		
		Object stage= restTemplate.getForObject(JENKINSURL+"/"+path, Object.class);	
		return stage;
		
	}
	
	
	public ChangeSet[] getCommitbyRunid(int id, HttpHeaders  headers) {
		RestTemplate restTemplate = new RestTemplate();
		//System.out.println("http://192.168.35.11:8080/job/CICD Competency/"+ id +"/wfapi/changesets");
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));
		ChangeSet[] result = restTemplate.getForObject(JENKINSURL+"/job/mdthelloworld/"+ id +"/wfapi/changesets",
				ChangeSet[].class);
		System.out.println(result);
		return result;

	}
	
	public Object getSonarQubeRules() {
		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));		
		Object stage= restTemplate.getForObject(SONARURL+"/api/qualitygates/show?id=6", Object.class);	
		return stage;
		
	}
	
	
	
	
	 

}
