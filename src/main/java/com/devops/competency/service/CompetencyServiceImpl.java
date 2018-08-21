package com.devops.competency.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
		Run run []= restTemplate.getForObject(JENKINSURL+"/job/mdthelloworld/wfapi/runs", Run[].class);	
		return run;
		
	}
	
	public Run[] getAllRuns(String jobName) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(JENKINSUSERNAME, JENKINSPASSWORD));		
		Run run []= restTemplate.getForObject(JENKINSURL+"/job/"+ jobName +"/wfapi/runs", Run[].class);	
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
		
		Object stage= restTemplate.getForObject(SONARURL+"/api/qualitygates/show?name="+SONARPROJECTNAME, Object.class);	
		logger.info("stages list from sonar "+stage.toString() );

		return stage;
		
	}

	/*public String getlastBuildNumber(String jobname) throws JSONException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(JENKINSUSERNAME, JENKINSPASSWORD));
		String restUrl = JENKINSURL + "/job/" + jobname + "/api/json?tree=lastBuild[number]";
		logger.info("getlastBuildNumber :: REST URL {} ", restUrl);
		String lastBuildNumberJson = restTemplate.getForObject(restUrl, String.class);
		JSONObject json = new JSONObject(lastBuildNumberJson);
		if (json.has("lastBuild") && json.getJSONObject("lastBuild").has("number")) {
			return json.getJSONObject("lastBuild").get("number").toString();
		} else
			return null;
	}*/

	/*public Run getlastRunStageStatus(String jobname, String lastRunSequenceId) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(JENKINSUSERNAME, JENKINSPASSWORD));
		String restUrl = JENKINSURL + "/job/" + jobname + "/" + lastRunSequenceId + "/api/wfapi/describe";
		logger.info("getlastRunStageStatus :: REST URL {} ", restUrl);
		Run run = restTemplate.getForObject(restUrl, Run.class);
		return run;
	}*/

	/*public boolean isLastBuildSucessfullyDeployed(String jobname, String deployedStageName) throws Exception {
		String lastbuild = getlastBuildNumber(jobname);
		Run run = getlastRunStageStatus(jobname,lastbuild);
		for(Stage stage : run.getStages()) {
			if(stage.getName().equals(deployedStageName) && stage.getStatus().equals(Status.SUCESS.toString())) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}*/
}
