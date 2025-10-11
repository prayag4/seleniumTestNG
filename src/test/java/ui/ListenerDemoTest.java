package ui;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import common.BaseTest;


public class ListenerDemoTest extends BaseTest{

    // @Test(retryAnalyzer = common.Retry.class) //retryAnalyzer for retry method test case wise
    @Test
    public void launchApp(){
        Reporter.log("Going to website");
        driver.get("https://ebay.com");
        Assert.assertTrue(false);
    }
    
}
