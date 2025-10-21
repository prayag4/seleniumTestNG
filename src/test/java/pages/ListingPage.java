package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListingPage extends BasePage{
    private String listingURL = baseURL + "records";
    private final By addButton = By.className("bg-green-500");


    //constructor
    public ListingPage(WebDriver driver) {
        super(driver);
    }

    public void gotoListingPage(){
        goTo(listingURL);
    }

    public void clickOnAddButton(){
        clickWhenClickable(addButton);
    }

    public String getLatestTableValue(String fieldName , String fieldValue){
        List<String> fieldNames = getAllFieldNamesInTable();
        if(fieldNames.contains(fieldName)){
            int indexOfField = fieldNames.indexOf(fieldName);
            String text = waitForTableCellText(indexOfField, fieldValue);
            return text;
        }
        return "No data";
    }

    
}
