Quick Java Validator
====================

Add-on for string validation. ( TestProject [deprecated] ) 

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

    
