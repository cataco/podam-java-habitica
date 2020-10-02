package com.redreader.util;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/**
 * This class provides services to start and stop Appium server using code, and not to manage it outside framework.
 */
public class AppiumServer {

    private static AppiumDriverLocalService appiumServer;

    public static void start(int port) {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.usingPort(port);
        appiumServer = AppiumDriverLocalService.buildService(builder);
        appiumServer.start();
    }

    public static void stop() {
        if(appiumServer.isRunning())
            appiumServer.stop();
    }
}