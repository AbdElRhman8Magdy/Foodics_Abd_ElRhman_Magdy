package com.Foodics.Amazon.base;

import com.Foodics.Amazon.base.utils.CookieUtils;
import com.Foodics.Amazon.factory.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BaseTest {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public WebDriver getDriver() {
        return this.driver.get();
    }
    @BeforeMethod
    public void Setup() {
        WebDriver driver = new DriverFactory().DriverInitialize();
        setDriver(driver);
    }
    @AfterMethod
    public void Close(ITestResult result) throws InterruptedException {
        String testCaseName = result.getMethod().getMethodName();
        File destFile = new File("target" + File.separator + "ScreenShots" + File.separator + testCaseName + ".png");
        takeScreenShoot(destFile);
        Thread.sleep(2000);
        getDriver().quit();
    }
    @Step
    public void injectCookiestoBrowser(List<Cookie> ResrAssuredCokkies) {
        List<org.openqa.selenium.Cookie> SeleniumCookie = CookieUtils.ConvertRestAssuredcookiestoSeliniumCookies(ResrAssuredCokkies);

        for (org.openqa.selenium.Cookie cookie : SeleniumCookie) {
            getDriver()
                    .manage().addCookie(cookie);
        }
    }
    @Step
    public void takeScreenShoot(File destFile) {

        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, destFile);
            InputStream iS = new FileInputStream(destFile);
            Allure.addAttachment("screenshot", iS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
