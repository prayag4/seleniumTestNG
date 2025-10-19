package tests;

import common.BaseTest;
import pages.FormPage;
import pages.ListingPage;

import java.util.HashMap;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.RandomUtility;

public class CrudPOMTest extends BaseTest {

    FormPage formPage;
    ListingPage listingPage;
    RandomUtility randomUtility = new RandomUtility();
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void setupPages() {
        formPage = new FormPage(driver);      //need to put in Before class method otherwise will face intialization error
        listingPage = new ListingPage(driver);
    }

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

    @Test(description = "Verify record can be added")
    public void testingCrud() {
        listingPage.gotoListingPage();
        listingPage.clickOnAddButton();
    }
}
