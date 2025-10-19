package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class BasePage {
    String baseURL = System.getProperty("baseURL");

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goTo(String url) {
        driver.get(url);
    }

    public WebElement findElement(By selector) {
        WebElement element = this.driver.findElement(selector);
        return element;
    }

    public List<WebElement> findElements(By selector) {
        List<WebElement> element = this.driver.findElements(selector);
        return element;
    }

    public WebElement findElementWithWait(By selector) {
        WebElement element = this.wait.until(ExpectedConditions.elementToBeClickable(selector));
        return element;
    }

    public List<WebElement> findElementsWithWait(By selector) {
        List<WebElement> element = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
        return element;
    }

    public void clickWhenClickable(By selector) {
        wait.until(ExpectedConditions.elementToBeClickable(selector)).click();
    }

    public void sendKeysWhenReady(By selector, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selector));
        element.clear();
        element.sendKeys(text);
    }

    public void selectByVisibleText(By selector, String text) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(selector));
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    public void switchToIframe(By iframeSelector) {
        WebElement iframe = wait.until(ExpectedConditions.elementToBeClickable(iframeSelector));
        driver.switchTo().frame(iframe);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String waitForNonEmptyText(By selector) {
        return wait.until(driver -> {
            String value = driver.findElement(selector).getText();
            return (value != null && !value.trim().isEmpty()) ? value : null;
        });
    }

    public String waitForNonEmptyAttribute(By selector, String attribute) {
        return wait.until(driver -> {
            String value = driver.findElement(selector).getAttribute(attribute);
            return (value != null && !value.trim().isEmpty()) ? value : null;
        });
    }

    public String waitForTableCellText(By tableSelector, int cellIndex, String expectedText) {
        return wait.until(driver -> {
            List<WebElement> lastRowTds = driver.findElements(By.cssSelector("table tr:last-child td"));
            if (lastRowTds.size() > cellIndex) {
                String text = lastRowTds.get(cellIndex).getText();
                return (text != null && !text.trim().isEmpty() && expectedText.equals(text)) ? text : null;
            }
            return null;
        });
    }

    public void clickRadioByValue(By radioSelector, String valueToClick) {
        List<WebElement> radios = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(radioSelector));
        for (WebElement radio : radios) {
            if (radio.getAttribute("value").equals(valueToClick)) {
                radio.click();
                break;
            }
        }
    }

    public void uploadFile(By fileInputSelector, String filePath) {
        WebElement fileInput = wait.until(ExpectedConditions.elementToBeClickable(fileInputSelector));
        fileInput.sendKeys(filePath);
    }

    public void typeInput(WebElement element, String formDataValue) {
        element.sendKeys(formDataValue);
    }

    public void clickWithJavaScript(By selector) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selector));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public List<String> getAllFieldTextsInTable(By selector) {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
        return elements.stream().map(WebElement::getText).toList();
    }

    public void rejectConfirmDialog() {
        // ((JavascriptExecutor) driver).executeScript("window.confirm = function(){
        // return false; }");
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }

    public void acceptConfirmDialog() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public WebElement findChildElement(By parentSelector, By childSelector) {
        WebElement parent = wait.until(ExpectedConditions.visibilityOfElementLocated(parentSelector));
        return parent.findElement(childSelector);
    }

    public void waitMilliseconds(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickButtonInTableRow(String cellText, By buttonSelector) {
    List<WebElement> rows = driver.findElements(By.cssSelector("table tr"));
    for (WebElement row : rows) {
        if (row.getText().contains(cellText)) {
            WebElement button = row.findElement(buttonSelector);
            button.click();
            break;
        }
    }
}


    public WebElement findElementContainingText(By parentSelector, String partialText) {
        List<WebElement> elements = driver.findElements(parentSelector);
        for (WebElement element : elements) {
            if (element.getText().contains(partialText)) {
                return element;
            }
        }
        return null;
    }

    public void selectDate(String day, String month, String year) {
        WebElement yearDropdown = this.wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".react-datepicker__year-dropdown-container")));
        yearDropdown.click();

        WebElement yearOption = this.wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'react-datepicker__year-option') and text()='" + year + "']")));
        yearOption.click();

        WebElement monthDropdown = this.wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".react-datepicker__month-dropdown-container")));
        monthDropdown.click();

        WebElement monthOption = this.wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'react-datepicker__month-option') and text()='" + month + "']")));
        monthOption.click();

        String paddedDay = String.format("%02d", Integer.parseInt(day));
        String dateLocator = String.format(
                "div[class*='react-datepicker__day--0%s'][aria-label*='%s %s']",
                paddedDay, month, day);

        WebElement dayElement = this.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(dateLocator)));
        dayElement.click();
    }
}
