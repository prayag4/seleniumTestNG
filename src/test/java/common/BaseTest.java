package common;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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


    public void selectDate(WebDriver driver, String day, String month, String year) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // 1. Select year
    WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
        By.cssSelector(".react-datepicker__year-dropdown-container")));
    yearDropdown.click();

    WebElement yearOption = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//div[contains(@class,'react-datepicker__year-option') and text()='" + year + "']")));
    yearOption.click();

    // 2. Select month
    WebElement monthDropdown = wait.until(ExpectedConditions.elementToBeClickable(
        By.cssSelector(".react-datepicker__month-dropdown-container")));
    monthDropdown.click();

    WebElement monthOption = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//div[contains(@class,'react-datepicker__month-option') and text()='" + month + "']")));
    monthOption.click();

    // 3. Select day
    String paddedDay = String.format("%02d", Integer.parseInt(day)); // pad with 0 if needed
    String dateLocator = String.format(
        "div[class*='react-datepicker__day--0%s'][aria-label*='%s %d']",
        paddedDay, month, Integer.parseInt(day)
    );

    WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(dateLocator)));
    dayElement.click();
}
}
