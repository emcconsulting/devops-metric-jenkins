package com.devops.competency.utils;

import java.util.function.Predicate;
import org.springframework.stereotype.Component;

/**
 * This class is meant to write a set of validation rules 
 * to be applied to an incoming request.
 * Any new rule can be added to this class as a method.
 * These are the set of the generic rules can be applied over
 * to any incoming object on the basis of the validation file input.
 * @author lohana1
 *
 */
@Component
public class RuleDefinition {

	public static Predicate<Object> nonZero(String path) {
		System.out.println("inside NonZero "+path);
		return p -> Integer.parseInt(Validator.getPropertyValue(p, path)) > 0;
	}
	
	public static Predicate<Object> isExist(String path){
		System.out.println("inside isExist "+path);
		return p-> !Validator.getPropertyValue(p, path).isEmpty();
	}

	public static boolean validate(Object object, Predicate<Object> predicate) {
		System.out.println("Inside validate ");
		return predicate.test(object);
	}

}
