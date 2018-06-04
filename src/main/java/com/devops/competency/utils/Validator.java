package com.devops.competency.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.devops.competency.dto.ValidationRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.devops.competency.dto.Rule;

@Component
public class Validator {

	static ValidationRule rule;
	static String currentPath=null;
	public static final String ELEMENT = "ns0:DataElement";
//	public static final String PATH = "C:\\Users\\lohana1\\Desktop\\DevOps\\gs-rest-service-master\\gs-rest-service-master\\complete\\src\\main\\resources\\validation.xml";
	static Map<String, String> xmlMapping = new LinkedHashMap<String, String>();
	static Map<String, Predicate<Object>> predicateRegister= new HashMap<String, Predicate<Object>>();

	static ArrayList<Rule> ruleList = new ArrayList<Rule>();

	static ObjectMapper objectMapper = new ObjectMapper();
	static Map<String, ArrayList<Predicate<Object>>> mapping = new HashMap<String, ArrayList<Predicate<Object>>>();
	static Map<String, Method> rules = new HashMap<>();

	/**
	 * validate an incoming resource 
	 * against the validation rule in the 
	 * system.
	 * @param object
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	public boolean validate(Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		boolean isValid = false;

		for (Rule rule : ruleList) {
			System.out.println(rule.getPath());
			ArrayList<Predicate<Object>> predicateList = mapping.get(rule.getPath());
			for (Predicate<Object> predicate : predicateList) {
				isValid = RuleDefinition.validate(object, predicate);
				System.out.println("validation : "+rule.getPath()+" "+isValid);
			}
			if (!isValid) {
				return false;
			}
		}
		return isValid;
	}

	/**
	 * Given a path and object 
	 * it returns you the value of 
	 * the field from object.
	 * @param object
	 * @param path
	 * @return
	 */
	public static String getPropertyValue(Object object, String path) {
		String value=null;
		System.out.println("Inside getPropertyValue" + path);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<?, ?> map = objectMapper.convertValue(object, LinkedHashMap.class);
		System.out.println("Parsing started");
		DocumentContext documentContext = JsonPath.parse(map);
		System.out.println("Parsing end"+ path);
		value= documentContext.read("$." + path);
		System.out.println("value retrieved");
		return value;

	}

	/**
	 * parse the XML from the rule.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	private static void parseRuleXML(String PATH)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException, ParserConfigurationException, SAXException {
		File fXmlFile = new File(PATH);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		NodeList nodeList = doc.getElementsByTagName(ELEMENT);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				String str[] = currentNode.getTextContent().split("\n");
				String path = str[1].trim();
				Rule rule = new Rule(path, str[2].trim());
				ruleList.add(rule);
				registerValidationRule(path, str[2].trim());

			}
		}
	}
	
	/**
	 * register validation rule
	 * takes a path as input 
	 * and registers the data
	 * with predicate to a list.
	 * @param path
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private static void registerValidationRule(String path, String name) throws NoSuchMethodException, SecurityException {
		ArrayList<Predicate<Object>> predicateList = mapping.get(path);	
			if (null == predicateList) {
				predicateList = new ArrayList<Predicate<Object>>();
				switch (name) {
				case "nonZero":
					predicateList.add(RuleDefinition.nonZero(path));
					break;
				case "isExist":
					predicateList.add(RuleDefinition.isExist(path));
					break;
				}
				System.out.println("predicate is null");
			}
			System.out.println("Putting path and predicate list to map: "+path);
			mapping.put(path, predicateList);
		
		System.out.println("Predicate stored in Map");
	}
	
/*	
	public static boolean validateInput(String value, String pattern) throws ParseException {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(value);
		if (m.find())
			return value.matches(pattern);
		System.out.println("Not Macth===" + value.matches(pattern));
		System.out.println("Validation end");
		return false;
	}

	public static boolean isExist(boolean condition, String path, Object object) {
		Map<?,? > map = objectMapper.convertValue(object, LinkedHashMap.class);
		DocumentContext documentContext = JsonPath.parse(map);
		String value = documentContext.read("$." + path);
		return condition & !value.isEmpty() ? false : true;

	}
*/
	@PostConstruct
	private void init() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ParserConfigurationException, SAXException {
//		parseRuleXML(PATH);
	}

}
