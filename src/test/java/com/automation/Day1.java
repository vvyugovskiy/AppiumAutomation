package com.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Day1 {

    private AppiumDriver<MobileElement> driver;

    @Test
    public void calculatorTest() throws Exception {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            // we use Android but could be iOS also
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");

            desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
            desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2"); // UiAutomator -> Like WebDriver we use to talk to native applications
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);

            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
            // 4723 - default Appium port

            MobileElement digit2 = driver.findElement(By.id("com.android.calculator2:id/digit_2"));
            MobileElement digit4 = driver.findElement(By.id("com.android.calculator2:id/digit_4"));
            MobileElement digit6 = driver.findElement(By.id("com.android.calculator2:id/digit_6"));
            MobileElement digit7 = driver.findElement(By.id("com.android.calculator2:id/digit_7"));

            MobileElement plus = driver.findElement(MobileBy.AccessibilityId("plus"));  // MobileBy -> child class of By class
            MobileElement minus = driver.findElement(MobileBy.AccessibilityId("minus"));
            MobileElement multiply = driver.findElement(MobileBy.AccessibilityId("multiply"));
            MobileElement divide = driver.findElement(MobileBy.AccessibilityId("divide"));

            MobileElement equals = driver.findElement(MobileBy.AccessibilityId("equals"));
            MobileElement result = driver.findElement(By.id("com.android.calculator2:id/result"));

            digit2.click();
            plus.click();
            digit2.click();
            equals.click();

            String resultText =  result.getText();
            Assert.assertEquals("4",resultText);

            getDigit(4).click();
            multiply.click();
            getDigit(5).click();
            equals.click();

             resultText = result.getText();
             Assert.assertEquals("20",resultText);

            getDigit(1).click();
            getDigit(5).click();
            divide.click();
            getDigit(3).click();
            equals.click();

            resultText = result.getText();
            Assert.assertEquals("5",resultText);

            getDigit(5).click();
            getDigit(1).click();
            minus.click();
            getDigit(3).click();
            getDigit(6).click();
            equals.click();

            resultText = result.getText();
            Assert.assertEquals("15",resultText);


        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.sleep(3000);
        driver.closeApp();

    }

    public MobileElement getDigit (int digit){
        return driver.findElement(By.id("com.android.calculator2:id/digit_"+digit));
    }
}
