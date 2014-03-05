package com.Orvyl.addons.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public final class Validation {
	private Object valueToValidate;
	private String validationName;
	private ArrayList<String> paramForValidation = null;
	private boolean passes;
	
	public Validation(Object valueToValidate, String validationName) {
		this.valueToValidate = valueToValidate;
		this.validationName = validationName;
	}

	public void performValidation() throws InvocationTargetException{
		ValidationRules rules = ValidationRules.getRules();
		try {
			
			Method methodToCall = rules.getClass().getMethod(validationName + "Rule", Object.class, ArrayList.class);
			passes = (boolean) methodToCall.invoke(rules, valueToValidate, paramForValidation);
			
			if(passes)
				System.out.println(validationName + ": TRUE");
			else
				System.out.println(validationName + ": FALSE");
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setParamForValidation(String[] params) {
		paramForValidation = new ArrayList<String>();
		for(String val : params)
			paramForValidation.add(val);
	}
	
	public ArrayList<String> getParamForValidation() {
		return paramForValidation;
	}

	public boolean isPasses() {
		return passes;
	}
	
	public String getValidationName() {
		return validationName;
	}
	
	public void setValidationName(String validationName) {
		this.validationName = validationName;
	}
	
}
