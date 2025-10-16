package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import common.BaseTest;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtension;
// import io.github.bonigarcia.wdm.*;
import utilities.RandomUtility;

// command - mvn test -DsuiteXmlFile=testng.xml -DbaseURL=url
public class CrudTest extends BaseTest {

	String baseURL = System.getProperty("baseURL");
	RandomUtility randomUtility = new RandomUtility();
	WebDriverWait wait;
	SoftAssert softAssert = new SoftAssert();

	// selector list
	By singleLineSelector = By.id("singleLine");
	By multiLineSelector = By.id("multiLine");
	By editorIframeSelector = By.cssSelector("iframe[id*='tiny-react']");
	By editorSelector = By.cssSelector("body#tinymce");
	By numberSelector = By.cssSelector("input[id='number']");
	By emailSelector = By.xpath("//input[@id='email']");
	By phoneSelector = By.cssSelector("input[type*='tel']"); // contains method
	By singleSelectionSelector = By.cssSelector("#singleSelect");
	By multiSelectionSelector = By.cssSelector("#multiSelect");
	By fileFieldSelector = By.cssSelector("#file");
	By radioButtonSelector = By
			.xpath("//label[contains(text(), 'Radio Buttons')]/following-sibling::div/label/input[@type='radio']");
	By checkboxSelector = By.cssSelector("input[type='checkbox']");
	By datePickerSelector = By.cssSelector("#date");
	By dateRangeStartSelector = By.cssSelector("#dateRangeStart");
	By dateRangeEndSelector = By.cssSelector("#dateRangeEnd");
	By timePickerSelector = By.id("time");
	By locationSelector = By.cssSelector("#location");
	By saveButtonSelector = By.cssSelector("button[type='submit']");

	// create object for fields
	HashMap<String, Object> formData = new HashMap<>();
	{
		formData.put("singleLine", randomUtility.generateRandomString());
		formData.put("multiLine", randomUtility.generateMultipleLineContent());
		formData.put("editor", randomUtility.generateMultipleLineContent());
		formData.put("number", randomUtility.generateRandomNumberss());
		formData.put("email", randomUtility.generateRandomEmail());
		formData.put("phone", randomUtility.generateFakePhoneNumber());
		formData.put("time", randomUtility.generateRandomTime());
		formData.put("location", randomUtility.getRandomLatLong());
		formData.put("singleSelection", "Option 2");
		formData.put("multiSelection1", "Option 1");
		formData.put("multiSelection2", "Option 2");
		formData.put("datePicker", randomUtility.getRandomDate());
		formData.put("dateRange", randomUtility.getRandomDateRange());
	}

	@BeforeMethod
	public void setUpWait() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void testingCrud() {
		// go to URL
		driver.get(baseURL + "records");

		// click on add button
		driver.findElement(By.className("bg-green-500")).click();

		// find element and fill details in it
		WebElement singlelineElement = wait.until(ExpectedConditions.elementToBeClickable(singleLineSelector));
		// singlelineElement.sendKeys((String)formData.get("singleLine")); // if value
		// is Object HashMap<String, Object> formData = new HashMap<>();
		singlelineElement.sendKeys((String) formData.get("singleLine")); // if value is Object HashMap<String, Object>
																			// formData =
		// new HashMap<>();

		WebElement multilineElement = wait.until(ExpectedConditions.elementToBeClickable(multiLineSelector));
		multilineElement.sendKeys((String) formData.get("multiLine"));

		// switch to iframe
		WebElement iframeElement = wait.until(ExpectedConditions.elementToBeClickable(editorIframeSelector));
		driver.switchTo().frame(iframeElement);
		wait.until(ExpectedConditions.elementToBeClickable(editorSelector)).sendKeys((String) formData.get("editor"));

		// Switch back to the main document
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.elementToBeClickable(numberSelector)).sendKeys((String) formData.get("number"));
		wait.until(ExpectedConditions.elementToBeClickable(emailSelector)).sendKeys((String) formData.get("email"));

		wait.until(ExpectedConditions.elementToBeClickable(phoneSelector)).sendKeys((String) formData.get("phone"));
		wait.until(ExpectedConditions.elementToBeClickable(timePickerSelector)).sendKeys((String) formData.get("time"));
		wait.until(ExpectedConditions.elementToBeClickable(locationSelector))
				.sendKeys((String) formData.get("location"));

		// Single selection
		WebElement singleSelectionDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(singleSelectionSelector));
		Select select = new Select(singleSelectionDropdown);
		select.selectByVisibleText((String) formData.get("singleSelection"));

		// Multiple selection
		WebElement multiSelectDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(multiSelectionSelector));
		Select multiSelect = new Select(multiSelectDropdown);
		multiSelect.selectByVisibleText((String) formData.get("multiSelection1"));
		multiSelect.selectByVisibleText((String) formData.get("multiSelection2"));

		// Radio button
		List<WebElement> radioButtons = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(radioButtonSelector));
		for (int i = 0; i < (radioButtons).size(); i++) {
			System.out.println(radioButtons.get(i).getAttribute("value"));
			if (radioButtons.get(i).getAttribute("value").equals("option1")) {
				radioButtons.get(i).click();
				break;
			}
		}

		// Checkbox
		wait.until(ExpectedConditions.elementToBeClickable(checkboxSelector)).click();

		// file-Upload
		// System.out.println("Working dir: " + System.getProperty("user.dir"));
		File fileUpload = new File("src/test/java/resources/test.png");
		String absolutePath = fileUpload.getAbsolutePath();
		wait.until(ExpectedConditions.elementToBeClickable(fileFieldSelector)).sendKeys(absolutePath);

		// Date
		// Object[] object = (Object[]) formData.get("datePicker");
		// System.out.println(Arrays.deepToString(object));

		wait.until(ExpectedConditions.elementToBeClickable(datePickerSelector)).click();
		String[] daymonthyear = (String[]) ((Object[]) formData.get("datePicker"))[0];
		String day = daymonthyear[0];
		String month = daymonthyear[1];
		String year = daymonthyear[2];
		selectDate(driver, day, month, year);

		// Date range
		wait.until(ExpectedConditions.elementToBeClickable(dateRangeStartSelector)).click();
		String[] daymonthyearRange = (String[]) ((Object[]) formData.get("dateRange"))[0];
		String dayRange1 = daymonthyearRange[0];
		String monthRange1 = daymonthyearRange[1];
		String yearRange1 = daymonthyearRange[2];
		selectDate(driver, dayRange1, monthRange1, yearRange1);

		wait.until(ExpectedConditions.elementToBeClickable(dateRangeEndSelector)).click();
		String[] daymonthyearRange2 = (String[]) ((Object[]) formData.get("dateRange"))[2];
		String dayRange2 = daymonthyearRange2[0];
		String monthRange2 = daymonthyearRange2[1];
		String yearRange2 = daymonthyearRange2[2];
		selectDate(driver, dayRange2, monthRange2, yearRange2);

		// submit the record
		wait.until(ExpectedConditions.elementToBeClickable(saveButtonSelector)).click();

		// verify the record in table
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table tr:last-child td")));

		WebElement lastRow = driver.findElement(By.cssSelector("table tr:last-child"));
		List<WebElement> allTds = lastRow.findElements(By.tagName("td"));
		String singleLineInTable = allTds.get(1).getText();
		softAssert.assertEquals(singleLineInTable,(String) formData.get("singleLine"),"Check single line value is same or not");
		softAssert.assertAll();

	}
}
