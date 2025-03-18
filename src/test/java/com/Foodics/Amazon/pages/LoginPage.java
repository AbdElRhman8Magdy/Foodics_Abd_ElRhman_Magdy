package com.Foodics.Amazon.pages;

import com.Foodics.Amazon.base.BasePage;
import com.Foodics.Amazon.base.utils.ConfigUtils;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;


public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "nav-link-accountList")
    private WebElement LoginMenu;

    @FindBy(xpath = "//*[@type='email']")
    private WebElement UserNameField;

    @FindBy(xpath = "//*[@type='password']")
    private WebElement PasswordField;


    @FindBy(id = "continue")
    private WebElement GoToPassowrd;

    @FindBy(xpath = "//*[@name='password']")
    private WebElement PasswordInput;
    @FindBy(id = "signInSubmit")
    private WebElement SubmitButton;
    @FindBy(linkText = "Signup")
    private WebElement SignupBtn;
    private List<Cookie> RestAssurCookeis;

    public List<Cookie> getCookeis() {
        return this.RestAssurCookeis;
    }


    @Step("aaddded description mannually to Load loging page and login")
    public LoginPage Load() {
        driver.get(ConfigUtils.GetInstance().ReturnBaseURL());
        return this;
    }

    @Step("click and get login popup")
    public HomePage login() throws InterruptedException {

        Assert.assertTrue(LoginMenu.isDisplayed());

        LoginMenu.click();
        Assert.assertTrue(UserNameField.isDisplayed());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", UserNameField);
//    UserNameField.click();
        UserNameField.sendKeys(ConfigUtils.GetInstance().ReturnEmail());
        GoToPassowrd.click();
        Thread.sleep(10);
//    Assert.assertTrue(PasswordField.isDisplayed());

        PasswordField.click();
        PasswordField.sendKeys(ConfigUtils.GetInstance().ReturnPassword());
        SubmitButton.click();

//    String getCookeis;
//    driver.manage().getCookies();

        return new HomePage(driver);
    }


}
