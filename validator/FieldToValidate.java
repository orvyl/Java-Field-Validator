package com.Orvyl.addons.validator;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FieldToValidate {
	private Object valueToValidate;
	private String displayFieldName;
	private Map<String, Validation> validators = new HashMap<String, Validation>();
	private boolean fieldPassAllValidation = true;
	
	public Map<String, Validation> getValidators() {
		return validators;
	}
	
	public void setValidators(String[] listOfValidations) {
		Validation val;
		for(String validationAndParam : listOfValidations) {
			String[] parsedMethodAndParam = validationAndParam.split(":");
			String methodName = parsedMethodAndParam[0];
			val = new Validation(valueToValidate, methodName);
			if(parsedMethodAndParam.length == 2)
				val.setParamForValidation(parsedMethodAndParam[1].split(","));
			
			validators.put(methodName, val);
		}
	}
	
	public void startValidations() throws InvocationTargetException {
		for(Entry<String, Validation> entry : validators.entrySet()) {
			entry.getValue().performValidation();
			
			if(!entry.getValue().isPasses()) {
				if(fieldPassAllValidation)
					fieldPassAllValidation = false;
			}
		}
	}
	
	public boolean isFieldPassAllValidation() {
		return fieldPassAllValidation;
	}

	public Object getValueToValidate() {
		return valueToValidate;
	}
	
	public void setValueToValidate(Object valueToValidate) {
		this.valueToValidate = valueToValidate;
	}
	
	public String getDisplayFieldName() {
		return displayFieldName;
	}
	
	public void setDisplayFieldName(String displayFieldName) {
		this.displayFieldName = displayFieldName;
	}
}
