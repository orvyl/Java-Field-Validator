Quick Java Validator
====================

An easy-to-use validation for fields/variables expecially for GET/POST requests. I based this from both Code Igniter and Laravel validations.

how to use:
--------------

```sh
String valueToTest = "";

Validation val = Validation.getValidator();
val.add(valueToTest, "Display Name", "required|min:4|either:value1,value 2,next value to test");

if(val.fail()) {
  for(String errorMsg : val.getErrorMessages)
    System.out.println("errorMsg");
}
```

##### NOTE: This is still under development. When I'm done with v1.0.0, I will start accepting commits. :)

    
