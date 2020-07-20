package com.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class EtsyTest {

    private AppiumDriver<MobileElement> driver;

    @Test
    public void test() {

        try {

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel_2");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"/apps/etsy.apk");
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);
            driver = new AppiumDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),desiredCapabilities);
            Thread.sleep(2000);
            WebElement getStarted = driver.findElement(By.id("com.etsy.android:id/btn_link"));
            getStarted.click();

            Thread.sleep(3000);
            WebElement usernameField = driver.findElement(By.id("com.etsy.android:id/edit_username"));
            usernameField.sendKeys("v.vyugovskiy@mail.com");
            Thread.sleep(1000);
            WebElement passwordField = driver.findElement(By.id("com.etsy.android:id/edit_password"));
            passwordField.sendKeys("resi1986");
            WebElement loginButton = driver.findElement(By.id("com.etsy.android:id/button_signin"));
            loginButton.click();

            Thread.sleep(3000);
            driver.closeApp();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
