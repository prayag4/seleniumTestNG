package pages;

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

    
}
