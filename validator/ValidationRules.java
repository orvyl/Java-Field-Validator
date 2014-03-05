package com.Orvyl.addons.validator;

import java.util.ArrayList;

import com.Orvyl.addons.validator.exceptions.InvalidParameterToMethodException;

public final class ValidationRules {
	private static ValidationRules rules = null;
	
	public static ValidationRules getRules() {
		if(rules == null)
			rules = new ValidationRules();
		return rules;
	}
	
	public boolean requiredRule(Object valueToTest, ArrayList<String> param) {
		if(valueToTest.toString() == "")
			return false;
		return true;
	}
	
	public boolean minRule(Object valueToTest, ArrayList<String> param) throws InvalidParameterToMethodException {
		if(param == null) throw new InvalidParameterToMethodException("Invalid parameter for MIN validation(expecting an integer)!");

		return true;
	}
}
