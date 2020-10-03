package com.redreader;
//package <set your test package>;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
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
  private String testName = "Untitled";
  protected AndroidDriver<AndroidElement> driver = null;

  DesiredCapabilities dc = new DesiredCapabilities();

  @BeforeClass
  public void setUp() throws MalformedURLException {
    dc.setCapability("reportDirectory", reportDirectory);
    dc.setCapability("reportFormat", reportFormat);
    dc.setCapability("testName", testName);
    dc.setCapability(MobileCapabilityType.UDID, "emulator-5554");
    dc.setCapability(MobileCapabilityType.APP, "/Users/ccordob/Downloads/RedReader-limpia.apk");
    dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.quantumbadger.redreader");
    dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activities.MainActivity");
    driver = new AndroidDriver<>(dc);
    driver.setLogLevel(Level.INFO);
  }

  @Test
  public void validateAccountsModalIsDisplayed() throws InterruptedException {
    driver.findElement(By.id("android:id/button2")).click();
    driver.findElementByAccessibilityId("More options").click();
    driver.findElement(By.xpath("//*[@text='Accounts']")).click();
    driver.findElements(By.id("org.quantumbadger.redreader:id/recycler_item_text"))
        .forEach(element -> {
          Assert.assertTrue(element.isDisplayed());
        });
    /*driver.findElement(By.xpath("//*[@text='CLOSE']")).click();
    driver.findElement(By.xpath("//*[@contentDescription='More options']")).click();
    driver.findElement(By.xpath("//*[@text='Themes']")).click();
    driver.findElement(By.xpath("//*[@text='Blue']")).click();
    driver.findElement(By.xpath("//*[@text='Front Page']")).click();
    driver.findElement(By.xpath("//*[@text='Front Page' and ./parent::*[@class='android.widget.LinearLayout']]")).click();
    driver.findElement(By.xpath("//*[@id='actionbar_title_back_image']")).click();
    driver.findElement(By.xpath("//*[@text='All Subreddits']")).click();
    driver.findElement(By.xpath("//*[@text='All Subreddits' and ./parent::*[@class='android.widget.LinearLayout']]")).click();
    driver.findElement(By.xpath("//*[@id='actionbar_title_back_image']")).click();*/
  }

  @AfterTest
  public void tearDown() {
    driver.quit();
  }
}