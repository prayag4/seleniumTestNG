package pages;

// import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public void fillForm(HashMap<String, Object> formData) {
        if (formData.containsKey("singleLine")) {
            sendKeysWhenReady(singleLineSelector, ((String) formData.get("singleLine")));
        }
        if (formData.containsKey("multiLine")) {
            sendKeysWhenReady(multiLineSelector, ((String) formData.get("multiLine")));
        }
        if (formData.containsKey("editor")) {
            switchToIframe(editorIframeSelector);
            sendKeysWhenReady(editorSelector, ((String) formData.get("editor")));
            switchToDefaultContent();
        }
        if (formData.containsKey("number")) {
            sendKeysWhenReady(numberSelector, ((String) formData.get("number")));
        }
        if (formData.containsKey("email")) {
            sendKeysWhenReady(emailSelector, ((String) formData.get("email")));
        }
        if (formData.containsKey("phone")) {
            sendKeysWhenReady(phoneSelector, ((String) formData.get("phone")));
        }
        if (formData.containsKey("time")) {
            sendKeysWhenReady(timePickerSelector, ((String) formData.get("time")));
        }
        if (formData.containsKey("location")) {
            sendKeysWhenReady(locationSelector, ((String) formData.get("location")));
        }
        if (formData.containsKey("file")) {
            uploadFile(fileFieldSelector, (String) formData.get("file"));
        }
        if ((String) formData.get("singleSelection") == "random") {
            List<String> allOptions = getAllOptionValuesOfSelectDropdown(singleSelectionSelector);
            String selectedValue = randomUtility.getRandomSelectedOneValueFromArray(allOptions);
            selectByVisibleText(singleSelectionSelector, selectedValue);
            formData.put("singleSelection",selectedValue);
        }
        if ((String) formData.get("multiSelection") == "random") {
            List<String> allOptions = getAllOptionValuesOfSelectDropdown(multiSelectionSelector);
            List<String> selectedValues = randomUtility.getRandomSelectedValuesFromArray(allOptions);
            for (String optionValue : selectedValues) {
                selectByVisibleText(multiSelectionSelector, optionValue);
            }
            formData.put("multiSelection",selectedValues);

        }
        if ((String) formData.get("radio") == "random") {
            List<String> allOptions = getOptionsAttributeValue(radioButtonSelector);
            String selectedValue = randomUtility.getRandomSelectedOneValueFromArray(allOptions);
            clickByValue(radioButtonSelector, selectedValue);
            formData.put("radio",selectedValue);

        }
        if ((String) formData.get("checkbox") == "random") {
            List<String> allOptions = getOptionsAttributeValue(checkboxSelector);
            List<String> selectedValues = randomUtility.getRandomSelectedValuesFromArray(allOptions);
            for (String optionValue : selectedValues) {
                clickByValue(checkboxSelector, optionValue);
            }
            formData.put("checkbox",selectedValues);

        }
        if (formData.containsKey("datePicker")) {
            clickWhenClickable(datePickerSelector);
            String[] daymonthyear = (String[]) ((Object[]) formData.get("datePicker"))[0];
            String day = daymonthyear[0];
            String month = daymonthyear[1];
            String year = daymonthyear[2];
            selectDate(day, month, year);
        }
        if (formData.containsKey("dateRange")) {
            clickWhenClickable(dateRangeStartSelector);
            String[] daymonthyear = (String[]) ((Object[]) formData.get("dateRange"))[0];
            String day = daymonthyear[0];
            String month = daymonthyear[1];
            String year = daymonthyear[2];
            selectDate(day, month, year);

            clickWhenClickable(dateRangeEndSelector);
            String[] daymonthyear2 = (String[]) ((Object[]) formData.get("dateRange"))[2];
            String day2 = daymonthyear2[0];
            String month2 = daymonthyear2[1];
            String year2 = daymonthyear2[2];
            selectDate(day2, month2, year2);
        }

    }

    public void saveRecord(){
        clickWhenClickable(saveButtonSelector);
    }
}

