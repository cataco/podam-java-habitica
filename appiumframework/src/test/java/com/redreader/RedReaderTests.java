package com.redreader;
//package <set your test package>;
import static org.testng.Assert.assertTrue;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.junit.jupiter.api.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
    dc.setCapability(MobileCapabilityType.APP, "/Users/ccordob/appiumstudio/original-apks/org.quantumbadger.redreader.activities.MainActivity.2.apk");
    dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.quantumbadger.redreader");
    dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activities.MainActivity");
    driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
    driver.setLogLevel(Level.INFO);
  }

  @Test
  public void testUntitled() {
    //assertTrue();
  }

  @AfterTest
  public void tearDown() {
    driver.quit();
  }
}