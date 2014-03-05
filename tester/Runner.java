package com.Orvyl.addons.tester;

import com.Orvyl.addons.validator.Validator;

public class Runner {

	public static void main(String[] args) {
		String displayFieldName = "Sample Field";
		String valueToTest = "";
		
		Validator val = Validator.getValidator();
		val.add(valueToTest, displayFieldName, "required|min:8");
		val.setCustomErrorMessage("Test Field", "required", "This SAMPLE FIELD is required!");
		val.setCustomErrorMessage("Test Field", "min", "SAMPLE FIELD must be grater than 8");
		
		if(val.passes())
			System.out.println("Success!!!!");
	}

}
