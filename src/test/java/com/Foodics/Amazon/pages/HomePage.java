package com.Foodics.Amazon.pages;

import com.Foodics.Amazon.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id =  "nav-hamburger-menu")
    private WebElement AllMenu;
    @FindBy(css =  "[aria-label='See All Categories']")
    private WebElement SeeAll;
//    @FindBy(xpath =  "//*[contains(text(), 'All Video Games')]")
//    private WebElement VideoGames;
    @FindBy(xpath =  "//a[@class='hmenu-item']//div[contains(text(),'Video Games')]//following::i[1]")
    private WebElement VideoGames;
    @FindBy(xpath =  "//*[text()='All Video Games']")
    private WebElement AllVideoGames;
//@FindBy(xpath =  "//a[contains(@class, 'hmenu-item') and text()='All Video Games']")
//private WebElement AllVideoGames;

    public VideoGames OpenAllMenu() throws InterruptedException {
        Assert.assertTrue(AllMenu.isDisplayed());

        AllMenu.click();
        SeeAll.click();
//        VideoGames.wait(500);
//        Assert.assertFalse(VideoGames.isDisplayed());

        VideoGames.click();

       // Assert.assertTrue(AllVideoGames.isDisplayed());
        Thread.sleep(500);
        Assert.assertTrue(AllVideoGames.isDisplayed());
        System.out.println(AllVideoGames.getText());
        System.out.println(AllVideoGames.isDisplayed());
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", AllVideoGames);
//        AllVideoGames.click();


        return new VideoGames(driver);
    }
}
