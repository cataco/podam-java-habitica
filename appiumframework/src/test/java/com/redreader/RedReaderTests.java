package com.redreader;
//package <set your test package>;

import com.redreader.page.BasePage;
import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import org.junit.jupiter.api.*;

public class RedReaderTests {

  private String reportDirectory = "reports";
  private String reportFormat = "xml";
  private String testName = "RedReader";
  private BasePage basePage;
  protected AndroidDriver<AndroidElement> driver = null;

  DesiredCapabilities dc = new DesiredCapabilities();

  @BeforeClass
  public void setUp() throws MalformedURLException {
    dc.setCapability("reportDirectory", reportDirectory);
    dc.setCapability("reportFormat", reportFormat);
    dc.setCapability("testName", testName);
    dc.setCapability(MobileCapabilityType.UDID, "emulator-5554");
    dc.setCapability(MobileCapabilityType.APP, "/Users/ccordob/Downloads/RedReader-modificada.apk");
    dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.quantumbadger.redreader");
    dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activities.MainActivity");
    driver = new AndroidDriver<>(dc);
    driver.setLogLevel(Level.INFO);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    basePage = new BasePage(driver);
    driver.findElement(By.id("android:id/button2")).click();
  }

  @Test
  public void validateAccountsModalIsDisplayed() throws InterruptedException {
    driver.findElementByAccessibilityId("More options").click();
    basePage.waitVisibility(driver.findElement(By.xpath("//*[@text='Accounts']")));
    driver.findElement(By.xpath("//*[@text='Accounts']")).click();
    driver.findElements(By.id("org.quantumbadger.redreader:id/recycler_item_text"))
        .forEach(element -> {
          Assert.assertTrue(element.isDisplayed());
        });
    driver.findElement(By.id("android:id/button3")).click();
  }

  @Test
  public void validateThemesModalIsDisplayed() {
    ByAccessibilityId byAccessibilityId = new ByAccessibilityId("More options");
    basePage.waitExists(byAccessibilityId);
    driver.findElementByAccessibilityId("More options").click();
    basePage.waitVisibility(driver.findElement(By.xpath("//*[@text='Themes']")));
    driver.findElement(By.xpath("//*[@text='Themes']")).click();
    Assert.assertTrue(driver.findElement(By.id("android:id/alertTitle")).isDisplayed());
    driver.findElement(By.xpath("//*[@text='Red']")).click();
  }

  @Test
  public void validateFrontPageNavigation() {
    driver.findElement(By.xpath("//*[@text='Front Page']")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//*[@text='Front Page']")).isDisplayed());
    driver.findElement(By.id("org.quantumbadger.redreader:id/actionbar_title_back_image")).click();
  }

  @Test
  public void validateSubredditsNavigation() {
    driver.findElement(By.xpath("//*[@text='All Subreddits']")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//*[@text='All Subreddits']")).isDisplayed());
    driver.findElement(By.id("org.quantumbadger.redreader:id/actionbar_title_back_image")).click();
  }

  @Test
  public void validateSubredditPinnedToHomePage() {
    driver.findElement(By.xpath("//*[@text='art']")).click();
    ByAccessibilityId byAccessibilityId = new ByAccessibilityId("More options");
    basePage.waitExists(byAccessibilityId);
    driver.findElementByAccessibilityId("More options").click();
    driver.findElement(By.xpath("//*[@text='Pin to Main Menu']")).click();
    driver.findElement(By.id("org.quantumbadger.redreader:id/actionbar_title_back_image")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//*[@text='Pinned Subreddits']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//*[@text='/r/art']")).isDisplayed());
  }

  @AfterTest
  public void tearDown() {
    driver.quit();
  }
}