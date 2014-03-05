package com.Orvyl.addons.validator;

import java.util.List;

public class ValidationRules {
	private static ValidationRules rules = null;
	
	public static ValidationRules getRules() {
		if(rules == null)
			rules = new ValidationRules();
		return rules;
	}
	
	public String requiredRule(List<Object> param) {
		String errMsg = "";
		String valueToTest = param.get(0).toString();
		
		if(valueToTest == "") errMsg = ErrorMessage.REQUIRED.getErrorMessage();
		
		return errMsg;
	}
	
	public String minRule(List<Object> param) {
		System.out.println("**minRule");
		return "";
	}
	
	public String maxRule(List<Object> param) {
		System.out.println("**maxRule");
		return "";
	}
}
