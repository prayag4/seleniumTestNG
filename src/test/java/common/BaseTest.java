package common;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;;

// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;



public class BaseTest {
    public static WebDriver driver = null;

    @BeforeSuite
    public void launchBrowser(){
        WebDriverManager.chromedriver().setup();
        	// WebDriverManager.chromedriver().setup();
	// ChromeOptions options = new ChromeOptions();
	// options.addArguments("--headless"); // Headless mode
//	options.addArguments("--window-size=1920,1080");
	// WebDriver driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterSuite
    public void closeBrowser(){
        // driver.close();
    }
}
