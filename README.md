Quick Java Validator
====================
(based from Code Igniter and Laravel)
-------------------------------------

An easy-to-use validation for fields/variables expecially for GET/POST requests.

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

##### NOTE: This is still under development. After finishing version 1, I will start accepting commits. :)

    
