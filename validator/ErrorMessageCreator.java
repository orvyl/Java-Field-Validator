package com.Orvyl.addons.validator;

import java.util.ArrayList;

public final class ErrorMessageCreator {
	
	private static ErrorMessageCreator instance = null;
	
	public static ErrorMessageCreator getMessageCreator() {
		if(instance == null) instance = new ErrorMessageCreator();
		return instance;
	}
	
	public String requiredErrorMessage(String errMsgUser, String displayFieldName, ArrayList<String> param) {
		String msg = errMsgUser == "" ? DefaultErrorMessage.REQUIRED.getErrorMessage():errMsgUser;
		System.out.println("ORIGINAL MESSAGE: " + errMsgUser);
		System.out.println("OUTPUT from creator: " + msg.replaceAll(":fieldname", errMsgUser));
		return msg.replaceAll(":fieldname", errMsgUser);
	}
}
