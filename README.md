JAVA VALIDATION (based from Code Igniter and Laravel)
=====================================================

An easy-to-use validation class for java.

SAMPLE USE:
*****
String valueToTest = "";

Validation val = Validation.getValidator();
val.add(valueToTest, "Display Name", "required|min:4|either:value1,value 2,next value to test");

if(val.fail()) {
  for(String errorMsg : val.getErrorMessages)
    System.out.println("errorMsg");
}
*****

NOTE: It is still under development. Version 1.0.0 will be on saturday!
