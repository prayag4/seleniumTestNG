package testngPractice;

import org.testng.annotations.Test;

import org.testng.Assert;


public class DependencyTest {

    @Test
	 public void Test1(){
		 System.out.println("This is test1");
         Assert.assertTrue(false);
	}
    
    @Test(dependsOnMethods = "Test1")
	public void Test2(){
		 System.out.println("This is test2");
	}

    @Test(dependsOnMethods = "Test1",alwaysRun = true) //soft dependency , even though dependent test case fails, it will run
	public void Test3(){
		 System.out.println("This is test3");
	}
}
