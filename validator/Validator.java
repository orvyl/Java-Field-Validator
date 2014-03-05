package com.Orvyl.addons.validator;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.Orvyl.addons.validator.exceptions.InvalidFormatListValidationException;
import com.Orvyl.addons.validator.exceptions.NoDisplayFieldNameException;

public final class Validator {
	private Map<String, FieldToValidate> fieldsToValidate;
	private Map<String, Map<String, String>> customErrorMessages = new HashMap<String, Map<String,String>>();
	private Map<String, Map<String, String>> errorMessages = new HashMap<String, Map<String,String>>();
	
	public Validator() {
		fieldsToValidate = new HashMap<String, FieldToValidate>();
	}
	
	public static Validator getValidator() {
		return new Validator();
	}
	
	public void add(Object valueToValidate, String displayFieldName, String validations) {
		try {
			
			checkValidationParameters(displayFieldName, validations);
			
			FieldToValidate fldValidate = new FieldToValidate();
			fldValidate.setValueToValidate(valueToValidate);
			fldValidate.setDisplayFieldName(displayFieldName);
			fldValidate.setValidators(validations.split("\\|"));
			
			fieldsToValidate.put(displayFieldName, fldValidate);
			
		} catch (NoDisplayFieldNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatListValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checkValidationParameters(String displayFieldName, String validations) throws NoDisplayFieldNameException, InvalidFormatListValidationException {
		if(displayFieldName == "")
			throw new NoDisplayFieldNameException("Display field name is required! It is use as an ID and field name for error message.");
		if(!validations.matches("([a-z]+(:[a-z0-9]+(,[a-z0-9]+)*)*)(\\|[a-z]+(:[a-z0-9]+(,[a-z0-9]+)*)*)*"))
			throw new InvalidFormatListValidationException("Invalid format in declaring list of validations!");
	}
	
	public boolean passes() {
		boolean result = true;
		for(Entry<String, FieldToValidate> entry : fieldsToValidate.entrySet()){
			try {
				
				entry.getValue().startValidations();
				if(!entry.getValue().isFieldPassAllValidation())
					result = false;
				
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		if(!result)
			finalizeErrorMessages();
		
		return result;
	}
	
	public boolean fails() { return !passes(); }
	
	public void setCustomErrorMessage(String displayFieldName, String ruleName, String msg) {
		if(customErrorMessages.containsKey(displayFieldName)){
			customErrorMessages.get(displayFieldName).put(ruleName, msg);
			return;
		}
		
		Map<String, String> valAndMessage = new HashMap<String, String>();
		valAndMessage.put(ruleName, msg);
		customErrorMessages.put(displayFieldName, valAndMessage);
	}
	
	public void finalizeErrorMessages() {
		for(Entry<String, FieldToValidate> entry : fieldsToValidate.entrySet()) {
			if(!entry.getValue().isFieldPassAllValidation()) {
				String  displayFieldName = entry.getValue().getDisplayFieldName();
				Map<String, String> specificErrors = new HashMap<String, String>();
				
				Map<String, Validation> validators = entry.getValue().getValidators();
				for(Entry<String, Validation> validator : validators.entrySet()) {
					if(!validator.getValue().isPasses()) {
						String ruleName = validator.getValue().getValidationName();
						System.out.println("FAIL IN: " + ruleName);
						specificErrors.put(ruleName, finalErrorMessage(ruleName, displayFieldName, validator.getValue().getParamForValidation()));
					}
				}
				errorMessages.put(displayFieldName, specificErrors);
			}
		}
		System.out.println(errorMessages);
	}
	
	public String finalErrorMessage(String ruleName, String displayFieldName, List<String> methodParam) {
		if(customErrorMessages.containsKey(displayFieldName)) {
			if(customErrorMessages.get(displayFieldName).containsKey(ruleName))
				return customErrorMessages.get(displayFieldName).get(ruleName);
		}
		return "";
	}

	public Map<String, Map<String, String>> getCustomErrorMessages() {
		return customErrorMessages;
	}
}
