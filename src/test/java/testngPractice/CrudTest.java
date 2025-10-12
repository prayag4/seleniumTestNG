package testngPractice;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.*;

public class CrudTest {
	//command - mvn test -DsuiteXmlFile=testng.xml -DbaseURL=url
	String baseURL = System.getProperty("baseURL");

	
	@Test
	public void testingCrud() {
		
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	// options.addArguments("--headless"); // Headless mode
//	options.addArguments("--window-size=1920,1080");
	WebDriver driver = new ChromeDriver(options);
	driver.get(baseURL+"records");

	
	driver.quit();

	}
}
