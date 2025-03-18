package com.Foodics.Amazon.pages;

import com.Foodics.Amazon.base.BasePage;
import com.Foodics.Amazon.base.utils.ConfigUtils;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartDetails extends BasePage {
    /**
     * 
     * @param driver
     */
    public CartDetails(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@name='proceedToRetailCheckout']")////*[normalize-space()='Proceed to Buy']
    private WebElement CheckOut;

    @FindBy(xpath = "//a[normalize-space()='Add a new delivery address']")
    private WebElement AddAddress;
    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressFullName']")
    private WebElement FullNameInput;
    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPhoneNumber']")
    private WebElement PhoneInput;
    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine1']")
    private WebElement StreetInput;
    @FindBy(xpath = "//input[@id='address-ui-widgets-enter-building-name-or-number']")
    private WebElement BuildingNoInput;
    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressCity']") //li[id='address-ui-widgets-autoCompleteResult-0']
    private WebElement CityInput;
    @FindBy(css = "li[id='address-ui-widgets-autoCompleteResult-0']")
    private WebElement CitySelectInput;
    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressDistrictOrCounty']")
    private WebElement DistrictInput;
    @FindBy(css = "li[id='address-ui-widgets-autoCompleteResult-0']]") //li[id='address-ui-widgets-autoCompleteResult-0']
    private WebElement DistrictSelectInput;
    @FindBy(xpath = "//input[@id='address-ui-widgets-landmark']")
    private WebElement LandMarkInput;
    @FindBy(xpath = "//span[@data-checkout-continue-button-click-action='{\"showTransition\":false}']//input[@type='submit']")
    private WebElement SubmitBTN;





    @Step
    public void ViewCart() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", CheckOut);
//        CheckOut.click();

        AddAddress.click();
        FullNameInput.sendKeys(Faker.instance().name().fullName());
        PhoneInput.sendKeys(ConfigUtils.GetInstance().ReturnEmail());
        StreetInput.sendKeys("Taksim el kuddah");
        BuildingNoInput.sendKeys(Faker.instance().address().buildingNumber());
        CityInput.click();
        CityInput.sendKeys("Sidi Gabir");
//        CityInput.click();
        Thread.sleep(500);
        CitySelectInput.click();
        Thread.sleep(500);

        DistrictInput.click();
        DistrictInput.sendKeys("Smouha");
        Thread.sleep(500);
        DistrictSelectInput.click();
        LandMarkInput.sendKeys(Faker.instance().university().name());
        SubmitBTN.click();


    }


}

