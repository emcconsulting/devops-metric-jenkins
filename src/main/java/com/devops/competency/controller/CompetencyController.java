package com.devops.competency.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.competency.dao.Rule;
import com.devops.competency.dao.SonarQube;
import com.devops.competency.dto.ChangeSet;
import com.devops.competency.dto.ClubCommits;
import com.devops.competency.dto.Commit;
import com.devops.competency.dto.Project;
import com.devops.competency.dto.Projects;
import com.devops.competency.dto.Run;
import com.devops.competency.service.CompetencyServiceImpl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@RestController
/**
 * 
 * @author lohana1 This controller is resposible for providing rest API end
 *         point for project details, job details etc.
 *
 */
@CrossOrigin (maxAge = 30)
public class CompetencyController {

	
	public static final Log logger = LogFactory.getLog(CompetencyServiceImpl.class);

	
	public static Properties sonarComplianceRules= new Properties();

	public static Map<String, String> stageNames = new HashMap<String, String>();

	@Autowired
	CompetencyServiceImpl competencyServiceImpl;

	public static List<Rule> complianceRules = new ArrayList<Rule>();

	public static Map<String, String> devComplianceRules = new HashMap<String, String>();

	/**
	 * This method return the Project object which is return by Jenkins API.
	 * 
	 * @return
	 */
	@RequestMapping("/project")
	public Project getProjectDetails() {
		logger.info("getting project details");
		try {
			return competencyServiceImpl.getProjectDetails();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			return new Project();
		}
		// return new Project(new Links(new Self("https://abc"), new
		// Runs("https://abc/describe")), "CICD Competancy", null, null);
	}

	/**
	 * This method return the details of each of the job running on a particular
	 * project.
	 */
	@RequestMapping("/projects")
	public Projects getProjects() {
		return competencyServiceImpl.getProject();

	}

	/**
	 * Get the details of the change set which triggered CICD job.
	 * 
	 * @param Runid
	 *            runID
	 * @return
	 */
	@RequestMapping("/changeset/{id}")
	public ChangeSet[] getCommitbyRunid(@PathVariable("id") int Runid, @RequestHeader HttpHeaders headers) {
		return competencyServiceImpl.getCommitbyRunid(Runid, headers);

	}

	/*
	 * This API will give you a set id all the stages of all the jobs in a project.
	 */
	@GetMapping("/jobs/all/stages")
	public Run[] getAllJobsStages() {
		logger.info("getting all stages");

		return competencyServiceImpl.getAllRuns();
	}
	
	

	/**
	 * get url of all the stages. These urls can be poll to get the details of each
	 * stage. check /stage API.
	 * 
	 * @return
	 */
	@RequestMapping("/stage/url")
	public JSONArray getStageUrl() {
		Run[] runs = competencyServiceImpl.getAllRuns();
		Gson gson = new Gson();
		logger.info("calling get property for stages" + gson.toJson(runs[0]));
		JSONArray stages = getPropertyValues(runs[0], "stages.._links.self.href");
		// System.out.println(stages.get(0));
		return stages;

	}

	/**
	 * Identify and return all stages of the pipeline.
	 * 
	 * @return
	 */
	@RequestMapping("/stage")
	public Map<String, String> getStages() {
		JSONArray stages = getStageUrl();
		for (Object stage : stages) {
			logger.info("=====NEW STAGE======" + stage.toString().replace("%20", " "));
			stage = competencyServiceImpl.getStageDetails(stage.toString().replace("%20", " "));
			if (isSonar(stage)) {

				String command = getPropertyValues(stage, "stageFlowNodes..parameterDescription").toString();
				String url = command.substring(command.lastIndexOf("url=") + 4, command.lastIndexOf("\"") - 1);
				stageNames.put("Sonar Stage", url.replace("\\", ""));
				logger.info("URL is" + command.toString().substring(command.toString().lastIndexOf("url=") + 4));

			}
			if (isJUnit(stage)) {
				String command = getPropertyValues(stage, "stageFlowNodes..parameterDescription").toString();
				stageNames.put("JUnit Stage", "mvn test");
			}
		}
		return stageNames;

	}

	/**
	 * get the sonar details.
	 * 
	 * @return
	 */
	@RequestMapping("/stage/sonar")
	public SonarQube getSonarStage() {
		SonarQube sonarDetails = competencyServiceImpl.getSonarQubeRulesForUI();
		return sonarDetails;

	}
	
	@RequestMapping("/compitency/sonar")
	public Object checkSonarCompetency() {
		Object sonarDetails=competencyServiceImpl.getSonarQubeRules();
		ObjectMapper objectMapper = new ObjectMapper();
		JSONArray sonarRulesJson=getPropertyValues(sonarDetails, "conditions.*");	
		logger.info("sonar rules "+ sonarRulesJson);
//		System.out.println("sonar Rules ************ "+sonarRulesJson);
		Gson gson= new Gson();
		Rule [] sonarRules=gson.fromJson(sonarRulesJson.toString(), Rule[].class);
//		Object sonarRules=objectMapper.convertValue(sonarRulesJson, Object.class);
		return checkRulesCompliance(sonarRules);
//		return sonarRules;
		
		}
	
	private String[] checkRulesCompliance(Rule[] sonarRules) {
		String [] reason= new String[sonarRules.length+1];
		int i=0;
		logger.info("Number of rules from sonar"+sonarRules.length);
		for (Rule rule: sonarRules){
			logger.info(sonarComplianceRules.toString());
			logger.info("Rule from sonar is "+ rule.getMetric());
			logger.info("Rule is "+ sonarComplianceRules.getProperty(rule.getMetric()));
			if(rule.getError().isEmpty()) {
				logger.info("rule is not set in sonar "+ rule.getMetric());
				reason[i++]="Not Set";
				continue;
			}
			
			if(Integer.parseInt(sonarComplianceRules.getProperty(rule.getMetric())) > Integer.parseInt(rule.getError())) {
				 reason[i++]=/*rule.getMetric()+" should be greater than"+ */"Non Compliance"/*+rule.getMetric().substring(4)+sonarComplianceRules.getProperty(rule.getMetric())*/;
			}
			else 
				reason[i++]= /*rule.getMetric() + "is compliance"+*/ "Compliance" /*sonarComplianceRules.getProperty(rule.getMetric())*/;	
			
			logger.info("reason set");

		}
		logger.info("Number of stages  check");
		if(competencyServiceImpl.getNumberofStages() > Integer.parseInt(sonarComplianceRules.getProperty("number_of_stages"))){
					reason[i]="Compliance";
		}
		else {
				reason[i]="Non Compliance";
		}
	
		logger.info("returnig comliance string");
		return reason;
	}


	/**
	 * Check stage is sonar stage or not.
	 * 
	 * @param command
	 * @return
	 */

	private static boolean isSonar(Object command) {

		if (command.toString().contains("sonar")) {

			logger.debug("===========YES SONAR=================");
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param command
	 * @return
	 */
	private static boolean isJUnit(Object command) {
		if (command.toString().contains("mvn test")) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param object
	 * @param path
	 * @return
	 */
	public static JSONArray getPropertyValues(Object object, String path) {

		logger.debug("Inside getPropertyValue " + path);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<?, ?> map = objectMapper.convertValue(object, LinkedHashMap.class);
		logger.debug("Parsing started ");
		DocumentContext documentContext = JsonPath.parse(map);
		logger.debug("Parsing end " + documentContext.jsonString());
		return documentContext.read("$." + path);
	}

	/**
	 * 
	 * @param object
	 * @param path
	 * @return
	 */
	public static String getPropertyValue(Object object, String path) {
		String value = null;
		logger.debug("Inside getPropertyValue" + path);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<?, ?> map = objectMapper.convertValue(object, LinkedHashMap.class);
		logger.debug("Parsing started");
		DocumentContext documentContext = JsonPath.parse(map);
		logger.debug("Parsing end" + path);
		value = documentContext.read("$." + path);
		logger.debug("value retrieved");
		return value;

	}

	/**
	 * Get the details of the
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws Exception
	 */

	@PostMapping("/commit")
	public ClubCommits postCommit(@RequestBody String httpEntity)
			throws JsonParseException, JsonMappingException, IOException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ClubCommits commits = null;
		ObjectMapper objectMapper = new ObjectMapper();
		Gson gson = new Gson();
		// try {
		Commit[] commit = null;
		commit = objectMapper.readValue(httpEntity, Commit[].class);

		commits = new ClubCommits(commit);

		System.out.println(gson.toJson(commit[0]));
		return commits;
		// boolean isValid= CommitValidator.validate(commit[0],
		// CommitValidator.IdNotZero());
		// System.out.println("Predicate Result is"+Boolean.toString(isValid));
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// try {
		// commits= objectMapper.readValue(httpEntity, ClubCommits.class);
		//
		// System.out.println("Hurrah its an Object");
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		//
		// System.out.println("Hurrah its an Object");
		// }
		// }

	}

	@PostMapping("/commit/plain")
	public Commit[] postCommit(@RequestBody Commit[] commit) {
		return commit;
	}

	@PostConstruct
	public void init() throws IOException {
		String resourceName="rule.properties";
		logger.info("Calling post construct");
		ClassLoader loader= Thread.currentThread().getContextClassLoader();
//		Properties rule=new Properties();
		InputStream resourceStream= loader.getResourceAsStream(resourceName);
//		rule.load(inStream);
//		InputStream input = new FileInputStream("rule.properties");
		sonarComplianceRules.load(resourceStream);
		logger.info("load completes total rules are"+ sonarComplianceRules.size());
	}

}
