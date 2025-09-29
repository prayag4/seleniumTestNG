package ui;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {
	
	
	 @Test
	 @Parameters("testVariable")
	 public void xmlParameter(String testVariable) {
	     System.out.println("Test variable is: " + testVariable);
	 }

}
