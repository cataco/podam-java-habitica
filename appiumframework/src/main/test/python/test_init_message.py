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

    def test_login_text(self):
        element = self.driver.find_element_by_id('android:id/message')
        self.assertEqual(element.text,
                         'You are not currently logged in. You can access the account list by selecting Accounts from the menu.',
                         'modal text does not match')
        self.driver.find_element_by_id('android:id/button2').click()
        time.sleep(3)

    def tearDown(self):
        self.driver.quit()

    if __name__ == '__main__':
        unittest.main()