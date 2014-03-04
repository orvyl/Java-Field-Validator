package com.Orvyl.addons.validation;

import java.util.List;

public class ValidationRules {
	private static ValidationRules rules = null;
	
	public static ValidationRules getRules() {
		if(rules == null)
			rules = new ValidationRules();
		return rules;
	}
	
	public boolean requiredRule(List<Object> param) {
		System.out.println("**requiredRule");
		String valueToTest = param.get(0).toString();
		if(valueToTest == "") return false;
		return true;
	}
	
	public boolean minRule(List<Object> param) {
		System.out.println("**minRule");
		return false;
	}
	
	public boolean maxRule(List<Object> param) {
		System.out.println("**maxRule");
		return false;
	}
}
