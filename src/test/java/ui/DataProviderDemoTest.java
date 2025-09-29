package ui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderDemoTest {
	
	@Test(dataProvider="create") // or use dataProvider="create"
	public void test(String username,String password) {
		
		System.out.println(username+password);
	}
	
	@Test(dataProvider="dataSetFunction2")
	public void test(String username,String password,String other) {
		
		System.out.println(username+password+other);
	}
	
	
	@DataProvider(name="create")
	public Object[][] dataSetFunction() {
		Object[][] dataset = new Object[2][2];
		
		//first row
		dataset[0][0] = "user1";
		dataset[0][1] = "pass1";
		
		//second row
		dataset[1][0] = "user2";
		dataset[1][1] = "pass2";
		
		
		return dataset;
		
	}
	
	@DataProvider
	public Object[][] dataSetFunction2() {
		return new Object[][] {{"user1","pass1","test1"},{"user2","pass2","test2"}};
	}
}
