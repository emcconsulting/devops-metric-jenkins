package com.devops.competency.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import com.devops.competency.dao.Rule;
import com.devops.competency.dao.SonarQube;
import com.devops.competency.dto.ChangeSet;
import com.devops.competency.dto.Project;
import com.devops.competency.dto.Projects;
import com.devops.competency.dto.Run;

@Service
public class CompetencyServiceImpl {
	public static final Logger logger = LoggerFactory.getLogger(CompetencyServiceImpl.class);
	
	@Value("${jenkins.url}")
	public String JENKINSURL;
	@Value("${sonar.url}")
	public String SONARURL;
	@Value("${sonar.project}")
	public String SONARPROJECTNAME;
	@Value("${jenkins.project}")
	public String JENKINSPROJECTNAME;
	@Value("${jenkins.username}")
	public String JENKINSUSERNAME;
	@Value("${jenkins.password}")
	public String JENKINSPASSWORD;
	
	
	public Project getProjectDetails() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(JENKINSUSERNAME, JENKINSPASSWORD));
		URI uri= new URI(null, null, null, 8080, "/job/"+JENKINSPROJECTNAME+"/wfapi", null, null);
		logger.info("calling jenkins uri "+JENKINSURL+ uri);
		Project result = restTemplate.getForObject(JENKINSURL+uri,
				Project.class);
		System.out.println(result);
		return result;
		// return new Project(new Links(new Self("https://abc"), new
		// Runs("https://abc/describe")), "CICD Competancy", null, null);
	}

	public Projects getProject() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(JENKINSUSERNAME, JENKINSPASSWORD));
		Projects result = restTemplate.getForObject(JENKINSURL+"/api/json", Projects.class);
		return result;
	}
	
	public Run[] getAllRuns() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(JENKINSUSERNAME, JENKINSPASSWORD));		
		Run run []= restTemplate.getForObject(JENKINSURL+"/job/"+JENKINSPROJECTNAME+"/wfapi/runs", Run[].class);	
		logger.info("number of stages"+run[0].getStages().size());
		return run;
		
	}
	
	public Object getStageDetails(String path) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(JENKINSUSERNAME, JENKINSPASSWORD));		
		Object stage= restTemplate.getForObject(JENKINSURL+"/"+path, Object.class);	
		return stage;
		
	}
	
	
	public ChangeSet[] getCommitbyRunid(int id, HttpHeaders  headers) {
		RestTemplate restTemplate = new RestTemplate();
		//System.out.println("http://192.168.35.11:8080/job/CICD Competency/"+ id +"/wfapi/changesets");
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(JENKINSUSERNAME, JENKINSPASSWORD));
		ChangeSet[] result = restTemplate.getForObject(JENKINSURL+"/job/mdthelloworld/"+ id +"/wfapi/changesets",
				ChangeSet[].class);
		System.out.println(result);
		return result;

	}
	
	public Object getSonarQubeRules() {
		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));
		
		Object rules= restTemplate.getForObject(SONARURL+"/api/qualitygates/show?name="+SONARPROJECTNAME, Object.class);
		logger.info("stages list from sonar "+rules.toString() );
		return rules;
		
	} 
	
	public SonarQube getSonarQubeRulesForUI() {
		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));
		
		SonarQube sonarQube= restTemplate.getForObject(SONARURL+"/api/qualitygates/show?name="+SONARPROJECTNAME, SonarQube.class);
		List<Rule> rules=sonarQube.getConditions();
		for  (Rule rule : rules) {
			rule.setMetric(rule.getMetric().replaceAll("_", " ").toUpperCase());
		}
		sonarQube.getConditions().add(new Rule("NUMBER OF STAGES", "", "", String.valueOf(getNumberofStages()), 1));
		logger.info("stages list from sonar "+sonarQube.toString() );
		return sonarQube;
		
	} 
	
	public Object changeStageName(Object stage) {
		
		return stage;
	}
	
	public int getNumberofStages() {
		Run[] runs=getAllRuns();
		logger.info("number of stages"+ runs[0].getStages().size());
		return runs[0].getStages().size();
	}
	
	

}
