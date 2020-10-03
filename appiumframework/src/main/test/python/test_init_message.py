import base64
import os
import unittest
import time
from appium import webdriver
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))


class Untitled(unittest.TestCase):
    reportDirectory = 'reports'
    reportFormat = 'xml'
    dc = {
        "platformName": "Android",
        "automationName": "UiAutomator2",
        "deviceName": "emulator-5556",
        "app": "C:\\Users\\usuario\\Downloads\\RedReader-limpia.apk"
    }
    testName = 'Untitled'
    driver = None

    def setUp(self):
        self.dc['reportDirectory'] = self.reportDirectory
        self.dc['reportFormat'] = self.reportFormat
        self.dc['testName'] = self.testName
        self.dc['udid'] = ''
        self.dc['platformName'] = 'android'
        self.driver = webdriver.Remote('http://localhost:4723/wd/hub', self.dc)
        self.driver.start_recording_screen()
        self.driver.start_recording_screen()
        self.filepath = os.path.join(BASE_DIR,
                                     "recording/test_login_text" + time.strftime("%Y_%m_%d_%H%M%S") + ".mp4")

    def test_login_text(self):
        element = self.driver.find_element_by_id('android:id/message')
        self.assertEqual(element.text,
                         'You are not currently logged in. You can access the account list by selecting Accounts from the menu.',
                         'modal text does not match')
        self.driver.find_element_by_id('android:id/button2').click()
        time.sleep(3)

    def tearDown(self):
        video_data = self.driver.stop_recording_screen()
        self.driver.quit()
        with open(self.filepath, 'wb') as rawdata:
            rawdata.write(base64.b64decode(video_data))


    if __name__ == '__main__':
        unittest.main()