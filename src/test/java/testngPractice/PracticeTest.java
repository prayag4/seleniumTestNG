package testngPractice;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.SuiteMethodClass;
import junit.framework.Assert;

import org.testng.SkipException;
import org.testng.annotations.*;


@Test(groups="all") //you can annote whole class with test as well
public class PracticeTest extends SuiteMethodClass {
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("before class, before any test case");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println(" after class,  after all test case");
	}
	
	@BeforeGroups(value="regression")
	public void beforeGroups() {
		System.out.println("before groups applied test case");
	}
	
	
	@AfterGroups(value="regression")
	public void afterGroups() {
		System.out.println("after groups applied test case");
	}
	
	@BeforeTest
	public void loginToApplication() {
		System.out.println("before test");
	}
	
	@AfterTest
	public void logoutToApplication() {
		System.out.println("after test");
	}
	
	
	@BeforeMethod
	public void performBeforetest() {
		System.out.println("before each method");
	}
	
	@AfterMethod
	public void performAftertest() {
		System.out.println("After each method");
	}
	
	
	
	 @Test(priority=2,groups="uat") //test nun test alphabetically but you can give priority
	 public void aTest(){
		 System.out.println("This is A test");

	}
	 @Test(priority=1,description="this is a logout test example",groups={"regression","uat"}) //run group with mvn test -Dgroups=uat
	 public void bTest(){
		 System.out.println("This is B test");
		 Assert.assertEquals(false, false);
		 
		 SoftAssert softassert = new SoftAssert();
		 softassert.assertEquals(false, false,"verify soft assertion"); //soft assertion which would not break the test due to assertion
		 softassert.assertEquals(true, true); //soft assertion which would not break the test due to assertion		 
		 softassert.assertAll(); //to report failure in test case
	}
	 
	 @Test(enabled=false) //skip the test
	 public void skipTest(){
		 System.out.println("This test will not run this test case");
	}
	 
	 @Test//skip the test with throw exception
	 public void skipTestWithException(){
		 System.out.println("This test will not run");
		 throw new SkipException("Skippping the test");
	}
	 
	 @Test//condition based exception
	 public void conditionBasedSkippingTestcase(){
		 Boolean dataSetup = true;
		 if(dataSetup==false) {
			 System.out.println("condition will run the test case");
		 }
		 else {
		 System.out.println("This test will not run");
		 throw new SkipException("Skippping the test");
		 
		 } 
	}
	 
	 @Test(dataProvider="dataSetFunction2",dataProviderClass=DataProviderDemoTest.class)
		public void test(String username,String password,String other) {
			
			System.out.println(username+password+other);
		}

	 
	 //To run particular test case - mvn test -Dtest=PracticeTest where PracticeTest is class file name
	 //To run file with xml confiugration - mvn test -DsuiteXmlFile=testng.xml
	 
}
