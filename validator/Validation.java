package com.Orvyl.addons.validator;

import java.util.List;

public final class Validation {
	private String validationName;
	private List<Object> validationParameter;
	
	public Validation(String validationName) {
		this.validationName = validationName;
	}
	
	public String getValidationName() {
		return validationName;
	}
	
	public void setValidationName(String validationName) {
		this.validationName = validationName;
	}
	
	public List<Object> getValidationParameter() {
		return validationParameter;
	}
	
	public void setValidationParameter(List<Object> validationParameter) {
		this.validationParameter = validationParameter;
	}
}
