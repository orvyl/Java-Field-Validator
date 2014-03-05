package com.Orvyl.addons.validator;

public enum ErrorMessage {
	
	REQUIRED(":fieldname is required."),
	MIN(":fieldname must be at least :minvalue character"),
	MAX(":fieldname must not be grater than :maxvalue character");
	
	private String errMsg;
	
	private ErrorMessage(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public String getErrorMessage() {
		return errMsg;
	}
}
