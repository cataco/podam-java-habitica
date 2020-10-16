package com.test;

import static org.testng.Assert.assertTrue;

import com.habitica.page.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import model.Habit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class HabiticaHabitsTest {

  private String reportDirectory = "reports";
  private String reportFormat = "xml";
  private String testName = "Habitica Habit flow";
  private BasePage basePage;
  protected AndroidDriver<AndroidElement> driver = null;

  DesiredCapabilities dc = new DesiredCapabilities();

  @BeforeClass
  public void setUp() throws MalformedURLException {
    dc.setCapability("reportDirectory", reportDirectory);
    dc.setCapability("reportFormat", reportFormat);
    dc.setCapability("testName", testName);
    dc.setCapability(MobileCapabilityType.UDID, "emulator-5554");
    dc.setCapability(MobileCapabilityType.APP, "/Users/ccordob/Downloads/habitica-3-0-1-1.apk");
    dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.habitrpg.android.habitica");
    dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ui.activities.MainActivity");
    driver = new AndroidDriver<>(dc);
    driver.setLogLevel(Level.INFO);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    basePage = new BasePage(driver);
  }

  @Test
  public void createHabitsTest() throws InterruptedException {
    driver.findElementById("com.habitrpg.android.habitica:id/skipButton").click();
    basePage.waitExists(By.id("com.habitrpg.android.habitica:id/show_login_button"));
    driver.findElementById("com.habitrpg.android.habitica:id/show_login_button").click();
    basePage.waitExists(By.id("com.habitrpg.android.habitica:id/username"));
    driver.findElementById("com.habitrpg.android.habitica:id/username").sendKeys("myname123fff");
    driver.findElementById("com.habitrpg.android.habitica:id/password").sendKeys("myname123fff");
    driver.findElementById("com.habitrpg.android.habitica:id/login_btn").click();
    basePage.waitExists(By.id("com.habitrpg.android.habitica:id/add_button"));
    for (int i = 0; i <= 10; i++) {
      PodamFactory factory = new PodamFactoryImpl();
      Habit habit = factory.manufacturePojo(Habit.class);
      System.out.println("Title: " + habit.getTitle());
      System.out.println("Notes: " + habit.getNotes());
      System.out.println("Notes: " + habit.getDifficulty());
      driver.findElementById("com.habitrpg.android.habitica:id/add_button").click();
      basePage.waitExists(By.id("com.habitrpg.android.habitica:id/text_edit_text"));
      driver.findElementById("com.habitrpg.android.habitica:id/text_edit_text")
          .sendKeys(habit.getTitle());
      driver.findElementById("com.habitrpg.android.habitica:id/notes_edit_text")
          .sendKeys(habit.getNotes());
      driver.navigate().back();
      driver.findElementByAccessibilityId(habit.getDifficulty()+", Not selected").click();
      driver.findElementById("com.habitrpg.android.habitica:id/action_save").click();
      basePage.waitExists(By.id("com.habitrpg.android.habitica:id/add_button"));
      assertTrue(driver.findElementByXPath("//*[@text='"+habit.getTitle()+"']").isDisplayed(),
          "Habit with title: " + habit.getTitle() + ". Notes: " + habit.getNotes()
              + ". Diifculty: "+ habit.getDifficulty() +". Was not created");
    }

  }


}
