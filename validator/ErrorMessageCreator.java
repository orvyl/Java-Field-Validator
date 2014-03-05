package com.Orvyl.addons.validator;

public final class ErrorMessageCreator {
	
	private static ErrorMessageCreator instance = null;
	
	public static ErrorMessageCreator getMessageCreator() {
		if(instance == null) instance = new ErrorMessageCreator();
		return instance;
	}
}
