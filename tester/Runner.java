package com.Orvyl.addons.tester;

import com.Orvyl.addons.validator.Validator;

public class Runner {

	public static void main(String[] args) {
		Validator val = Validator.getValidator();
		val.add("test", "Test Field", "required|min:34");
	}

}
