import base64
import os
import unittest
import time
from appium import webdriver
from appium.webdriver.common.touch_action import TouchAction
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
                                     "python/recording/test_menu_names" + time.strftime("%Y_%m_%d_%H%M%S") + ".mp4")

    def test_menu_options_subreddit(self):
        options = ["View in External Browser","Go to Subreddit", "Block Subreddit",
                   "Pin Subreddit to Main Menu", "Share", "Share Comments", "Copy", "User Profile", "Properties"]
        actions = TouchAction(self.driver)
        self.driver.find_element_by_id('android:id/button2').click()
        time.sleep(1)
        self.driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/"
                                          "android.widget.LinearLayout/android.widget.FrameLayout/"
                                          "android.widget.LinearLayout/android.widget.FrameLayout/"
                                          "android.widget.LinearLayout/android.widget.FrameLayout/"
                                          "android.widget.FrameLayout/android.widget.FrameLayout/"
                                          "android.view.ViewGroup/android.support.v7.widget.RecyclerView/"
                                          "android.widget.FrameLayout[2]/android.widget.LinearLayout/"
                                          "android.widget.LinearLayout/android.widget.TextView").click()
        time.sleep(10)
        element = self.driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/"
                                                    "android.widget.FrameLayout/android.widget.LinearLayout/"
                                                    "android.widget.FrameLayout/android.widget.LinearLayout/"
                                                    "android.widget.FrameLayout/android.widget.FrameLayout/"
                                                    "android.view.ViewGroup/android.support.v7.widget.RecyclerView/"
                                                    "android.widget.FrameLayout[2]/android.widget.LinearLayout/"
                                                    "android.widget.LinearLayout")
        actions.long_press(element)
        actions.perform()
        for i in range(1, 9):
            option = self.driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/"
                                                       "android.widget.FrameLayout/android.widget.FrameLayout/"
                                                       "android.widget.LinearLayout/android.widget.FrameLayout/"
                                                       "android.widget.ListView/android.widget.TextView[{}]".format(i))
            self.assertEqual(option.text, options[i - 1])





    def tearDown(self):
        video_data = self.driver.stop_recording_screen()
        self.driver.quit()
        with open(self.filepath, 'wb') as rawdata:
            rawdata.write(base64.b64decode(video_data))


    if __name__ == '__main__':
        unittest.main()