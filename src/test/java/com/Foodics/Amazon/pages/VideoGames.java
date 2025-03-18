package com.Foodics.Amazon.pages;

import com.Foodics.Amazon.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class VideoGames extends BasePage {
    public VideoGames(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class='a-cardui-body']")
    private WebElement AllResults;
    @FindBy(xpath = "//*[text()='Free Shipping']")
    private WebElement FreeShipping;

    @FindBy(xpath = "//span[@class='a-size-base a-color-base' and text()='New']")
    private WebElement NewCond;
    @FindBy(xpath = "//span[@class='a-size-base a-color-base a-text-bold'][normalize-space()='New']")
    private WebElement NewCondClicked;
    @FindBy(className = "a-dropdown-container")
    private WebElement SortDDW;

    @FindBy(xpath = "//*[text()='Price: High to Low']//ancestor::li")
    private WebElement SortHighToLow;
    @FindBy(xpath = "//span[@class='a-price']//span[@class='a-offscreen']")
    private WebElement ItemPrice;
    @FindBy(id = "attach-close_sideSheet-link")
    private WebElement closeAddToCartPopup;
    @FindBy(xpath = "//*[text()='Add to cart'][1]")
    private WebElement AddToCart1;

    @FindBy(id = "nav-cart-count-container")
    private WebElement Cart;


    @Step
    public void Filter() {

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        AllResults.click();
        jse.executeScript("arguments[0].click()", FreeShipping);

        jse.executeScript("arguments[0].click()", NewCond);

        Assert.assertTrue(NewCondClicked.isDisplayed());
//        jse.executeScript("arguments[0].click()", SortDDW);
//        jse.executeScript("arguments[0].click()", SortHighToLow);
        SortDDW.click();
        SortHighToLow.click();


    }

    @Step
    public boolean addProductsBelowPriceToCart(double maxPrice) throws InterruptedException {
        boolean itemsAdded = false;
        int pageNumber = 1;

        while (!itemsAdded && pageNumber <= 5) { // Limiting to 5 pages for demonstration
            List<WebElement> currentProductPrices = driver.findElements(By.xpath("//span[@class='a-price']//span[@class='a-offscreen']"));
            List<WebElement> currentProductTitles = driver.findElements(By.xpath("//a[@class='a-link-normal s-line-clamp-2 s-link-style a-text-normal']"));

            for (int i = 0; i < Math.min(currentProductPrices.size(), currentProductTitles.size()); i++) {
                try {
                    String priceText = currentProductPrices.get(i).getText().replace("EGP", "").replace(",", "").trim();
                    double price = Double.parseDouble(priceText);
                    System.out.println(currentProductPrices);
                    System.out.println("Price: " + price + " | Title: " + currentProductTitles.get(i).getText());
                    System.out.println("price is " + priceText + " " + price + " " + "Title is " + currentProductTitles);
                    if (price < maxPrice) {
                        System.out.println("Adding to cart: " + currentProductTitles.get(i).getText() + " - Price: " + price);
                        WebElement productContainer = currentProductTitles.get(i).findElement(By.xpath("//a[@class='a-link-normal s-line-clamp-2 s-link-style a-text-normal']//parent::div//parent::div"));
                        WebElement addToCartButton = productContainer.findElement(By.xpath("//*[text()='Add to cart']")); ////*[text()='Add to cart']  .//input[@class='a-button-input' and @title='Add to Cart']
                        addToCartButton.click();
//                        wait.until(ExpectedConditions.visibilityOf(addToCartConfirmation));
                        closeAddToCartPopup.click();
                        Thread.sleep(1000);
                        itemsAdded = true;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Could not parse price for a product.");
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    System.err.println("Could not find add to cart button for a product.");
                }
            }

            if (!itemsAdded) {
                System.out.println("No products below " + maxPrice + " EGP found on page " + pageNumber + ". Moving to the next page.");
                try {
                    WebElement nextButton = driver.findElement(By.xpath("//*[text()='Next']"));
                    nextButton.click();
//                    wait.until(ExpectedConditions.stalenessOf(currentProductPrices.get(0)));
                    Thread.sleep(2000);
                    pageNumber++;
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    System.out.println("No more pages found.");
                    break;
                }
            } else {
                break;
            }
        }
        AddToCart1.click();

        Cart.click();
        return itemsAdded;

    }


}
