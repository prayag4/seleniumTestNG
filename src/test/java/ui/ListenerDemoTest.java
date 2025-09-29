package ui;
import org.testng.Assert;
import org.testng.annotations.*;
import common.BaseTest;


public class ListenerDemoTest extends BaseTest{

    @Test
    public void launchApp(){
        driver.get("https://ebay.com");
        Assert.assertTrue(false);
    }
    
}
