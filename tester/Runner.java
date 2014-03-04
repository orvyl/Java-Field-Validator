package com.Orvyl.addons.tester;

import com.Orvyl.addons.validation.Validation;

public class Runner {

	public static void main(String[] args) {
		Validation val = Validation.getValidator();
		val.add(0, "TestFieldName", "required");
		// val.add(7, "We", "unique:tbl_user,columname");
	}

}
