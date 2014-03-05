package com.Orvyl.addons.validator;

import java.util.Map;

public class FieldToValidate {
	private Object valueToValidate;
	private String displayFieldName;
	private Map<String, Validation> validators;
	
	public Map<String, Validation> getValidators() {
		return validators;
	}
	
	public void setValidators(String[] parseValAndParam) {
		
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
