package com.Orvyl.addons.validator;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.Orvyl.addons.validator.exceptions.InvalidFormatListValidationException;
import com.Orvyl.addons.validator.exceptions.NoDisplayFieldNameException;

public final class Validator {
	private Map<String, FieldToValidate> fieldsToValidate;
	private Map<String, Map<String, String>> customErrorMessages = null;
	
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
		for(Entry<String, FieldToValidate> entry : fieldsToValidate.entrySet()){
			try {
				entry.getValue().startValidations();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public boolean fails() { return !passes(); }
	
	@SuppressWarnings("unchecked")
	public void setErrorMessage(String displayFieldName, String ruleName, String msg) {
		
		@SuppressWarnings("rawtypes")
		Map valAndMessage = new HashMap();
		
		valAndMessage.put(ruleName, msg);
		customErrorMessages.put(displayFieldName, valAndMessage);
	}

	public Map<String, Map<String, String>> getCustomErrorMessages() {
		return customErrorMessages;
	}
}
