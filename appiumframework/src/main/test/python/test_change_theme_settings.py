import unittest
import time
from appium import webdriver

class Untitled(unittest.TestCase):
    reportDirectory = 'reports'
    reportFormat = 'xml'
    dc = {}
    testName = 'Untitled'
    driver = None

    def setUp(self):
        self.dc['reportDirectory'] = self.reportDirectory
        self.dc['reportFormat'] = self.reportFormat
        self.dc['testName'] = self.testName
        self.dc['udid'] = ''
        self.dc['platformName'] = 'android'
        self.driver = webdriver.Remote('http://localhost:4723/wd/hub', self.dc)

    def test_settings_change_theme(self):
        self.driver.find_element_by_id('android:id/button2').click()
        time.sleep(1)
        self.driver.find_element_by_xpath("//android.widget.ImageView[@content-desc='More options']").click()
        time.sleep(1)
        self.driver.find_element_by_xpath(
            "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout").click()
        self.driver.find_element_by_xpath(
            "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.RelativeLayout").click()

        self.driver.find_element_by_xpath(
            "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout").click()

        self.driver.find_element_by_xpath(
            "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/ \
              android.widget.CheckedTextView[5]").click()
        self.driver.find_element_by_xpath(
            "//android.widget.ImageButton[@content-desc='Navigate up']").click()
        time.sleep(1)
        element = self.driver.find_element_by_id('android:id/alertTitle')
        self.assertEqual(element.text, "What's New")
        self.driver.find_element_by_id('android:id/button3').click()
        time.sleep(3)

    def tearDown(self):
        self.driver.quit()

    if __name__ == '__main__':
        unittest.main()
