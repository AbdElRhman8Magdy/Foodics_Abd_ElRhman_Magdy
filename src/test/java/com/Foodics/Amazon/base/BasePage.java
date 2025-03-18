package com.Foodics.Amazon.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {


    //set to be protected instead of private to be used by chiled -> LoginPage
    protected WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
}
