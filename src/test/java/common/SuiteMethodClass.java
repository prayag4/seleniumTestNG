package common;

import org.testng.annotations.*;

public class SuiteMethodClass {
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("before suite");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("after suite");
	}

	//Need to use with mvn test -DsuiteXmlFile=testng.xml
}
