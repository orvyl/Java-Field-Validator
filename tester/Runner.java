package com.Orvyl.addons.tester;

import com.Orvyl.addons.validator.Validator;

public class Runner {

	public static void main(String[] args) {
		String displayFieldName = "Sample Field";
		String valueToTest = "test";
		
		Validator val = Validator.getValidator();
		val.add(valueToTest, displayFieldName, "required|min:8");
		val.setErrorMessage("Test Field", "required", ":fieldname is required!");
		val.setErrorMessage("Test Field", "min", ":fieldname must be grater than 8");
		
		if(val.passes())
			System.out.println("Success!!!!");
	}

}
