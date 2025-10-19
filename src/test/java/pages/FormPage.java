package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.RandomUtility;

public class FormPage extends BasePage {

    public FormPage(WebDriver driver) {
        super(driver);
    }

    RandomUtility randomUtility = new RandomUtility();

    // selector list
    private final By singleLineSelector = By.id("singleLine");
    private final By multiLineSelector = By.id("multiLine");
    private final By editorIframeSelector = By.cssSelector("iframe[id*='tiny-react']");
    private final By editorSelector = By.cssSelector("body#tinymce");
    private final By numberSelector = By.cssSelector("input[id='number']");
    private final By emailSelector = By.xpath("//input[@id='email']");
    private final By phoneSelector = By.cssSelector("input[type*='tel']"); // contains method
    private final By singleSelectionSelector = By.cssSelector("#singleSelect");
    private final By multiSelectionSelector = By.cssSelector("#multiSelect");
    private final By fileFieldSelector = By.cssSelector("#file");
    private final By radioButtonSelector = By
            .xpath("//label[contains(text(), 'Radio Buttons')]/following-sibling::div/label/input[@type='radio']");
    private final By checkboxSelector = By.cssSelector("input[type='checkbox']");
    private final By datePickerSelector = By.cssSelector("#date");
    private final By dateRangeStartSelector = By.cssSelector("#dateRangeStart");
    private final By dateRangeEndSelector = By.cssSelector("#dateRangeEnd");
    private final By timePickerSelector = By.id("time");
    private final By locationSelector = By.cssSelector("#location");
    private final By saveButtonSelector = By.cssSelector("button[type='submit']");

    public void fillForm(){
        

    }

}
