package com.redreader.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * This class provides base functions to handle objects in pages.
 */
public class BasePage {

        protected WebDriver driver;
        protected WebDriverWait wait;

        //Here I have defined redundancy to read elements by ID using UIAutomator functions
        protected final static String ID_ = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceIdMatches(\".*";
        protected final static String _ID = ".*\"))";

        public BasePage (WebDriver driver){
            this.driver = driver;
            PageFactory.initElements(this.driver, this);
            wait = new WebDriverWait(driver,15);
        }

        public void waitVisibility(WebElement element) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }

        public void waitInvisibility(WebElement element) {
            wait.until(ExpectedConditions.invisibilityOf(element));
        }

        public void click (WebElement element) {
            waitVisibility(element);
            element.click();
        }

        public void writeText (WebElement element, String text) {
            waitVisibility(element);
            element.sendKeys(text);
        }

        public String readText (WebElement element) {
            waitVisibility(element);
            return element.getText();
        }

        public void assertEquals (WebElement element, String expectedText) {
            waitVisibility(element);
            Assert.assertEquals(readText(element), expectedText);
        }
}