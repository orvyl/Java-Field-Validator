/**
 * 
 */
package com.Orvyl.addons.validation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @title	Easy-to-use validation for Java (based from Code Igniter and Laravel)
 * @desc	This can validate basic rule like required, min/max character, natural number, etc. This is very useful especially for GET/POST fields.
 * @author	Orvyl F. Tumaneng
 * @version	1.0.0
 * @date	Feb 2014
 */
public class Validation {
	
	private Map<String, HashMap<String, String>> errorMessages;
	private boolean noError;
	
	public Validation() {
		errorMessages = new HashMap<String, HashMap<String, String>>();
		noError = true;
	}
	
	public static Validation getValidator() {
		return new Validation();
	}
	
	public void add(Object valueToValidate, String displayFieldName, String validations) {
		Map<String, String[]> methodAndParameter = parseValidationsAndParameters(validations);
		List<Object> methodParam;
		
		for(Entry<String, String[]> entry : methodAndParameter.entrySet()) {
			methodParam = new ArrayList<Object>();
			methodParam.add(valueToValidate);
			
			if(entry.getValue() != null){
				for(String param : entry.getValue())
					methodParam.add(param);
			}
			performValidation(entry.getKey(), methodParam, displayFieldName);
		}
	}
	
	public HashMap<String, String[]> parseValidationsAndParameters(String validations) {
		String[] listOfValidations = validations.split("\\|");
		HashMap<String, String[]> methodAndParameter = new HashMap<String, String[]>();
		for(String validationAndParam : listOfValidations) {
			if(validationAndParam == "") continue;

			String[] parsedMethodAndParam = validationAndParam.split(":");
			String methodName = parsedMethodAndParam[0];
			String[] methodParam = null;
			if(parsedMethodAndParam.length == 2)
				methodParam = parsedMethodAndParam[1].split(",");
			
			methodAndParameter.put(methodName,methodParam);
		}
		return methodAndParameter;
	}
	
	public void performValidation(String methodName, List<Object> param, String fieldDisplayName) {
		ValidationRules validateRule = ValidationRules.getRules();
		String defaultErrorMessage;
		try {
			
			Method methodToCall = validateRule.getClass().getMethod(methodName + "Rule", List.class);
			defaultErrorMessage = (String) methodToCall.invoke(validateRule, param);
			
			if(defaultErrorMessage != ""){
				if(noError) noError = false;
				
			}
			
		} catch (NoSuchMethodException e) {
			System.out.println("ERROR: Method: " + methodName + " is not suppported.");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("ERROR(method: performValidation): Got a SecurityException! " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("ERROR(method: performValidation): Got a IllegalAccessException! " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR(method: performValidation): Got a IllegalArgumentException! " + e.getMessage());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("ERROR(method: performValidation): Got a InvocationTargetException! " + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean isNoError() {
		return noError;
	}

	public void setNoError(boolean noError) {
		this.noError = noError;
	}

	public Map<String, HashMap<String, String>> getErrorMessage() {
		return errorMessages;
	}

	public void setErrorMessage(Map<String, HashMap<String, String>> errorMessage) {
		this.errorMessages = errorMessage;
	}
	
	// for testing purpose only
	public void displayMethodAndParameter(Map<String, String[]> methodAndParameter) {
		for(Entry<String, String[]> entry : methodAndParameter.entrySet()) {
			System.out.println("Method: " + entry.getKey());
			System.out.println("Parameters:");
			if(entry.getValue() == null) continue;
			for(String val : entry.getValue())
				System.out.println(val);
			System.out.println("===");
		}
	}
}
