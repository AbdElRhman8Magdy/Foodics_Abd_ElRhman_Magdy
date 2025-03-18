package com.Foodics.Amazon.pages;

import com.Foodics.Amazon.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JobDetails extends BasePage {
    public JobDetails(WebDriver driver) {super(driver);}
    @FindBy(xpath =  "//*[.='Fecha de Ingreso']//*[@class='oxd-input oxd-input--active']")
    private WebElement AddJoinDate;
    @FindBy(xpath =  "//*[.='Joined Date']//*[@class='oxd-input oxd-input--active']")
    private WebElement AddJoinDate1;
    @FindBy(xpath =  "//div[@class='oxd-grid-3 orangehrm-full-width-grid']//div[@class='oxd-select-wrapper']")
    private WebElement SelectJob1st;
    @FindBy(xpath =  "//div[@class='oxd-grid-3 orangehrm-full-width-grid']//div[@class='oxd-select-wrapper']//*[text()='Software Engineer']")
    private WebElement SelectJob;

    @FindBy(xpath = "//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[7]//div[@class='oxd-select-text-input']")
    private WebElement jopType;

    @FindBy(xpath = "//div[@class='oxd-grid-3 orangehrm-full-width-grid']//div[@class='oxd-select-wrapper']//*[text()='Part-Time Internship']")
    private WebElement jopTypepartTime;

    @FindBy(xpath = "//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[5]//div[@class='oxd-select-text-input']")
    private WebElement jobUnite;
    @FindBy(xpath = "//div[@class='oxd-grid-3 orangehrm-full-width-grid']//div[@class='oxd-select-wrapper']//*[text()='Quality Assurance']")
    private WebElement jobUniteassurance;

    @FindBy(xpath = "//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[6]//div[@class='oxd-select-text-input']")
    private WebElement location;
    @FindBy(xpath = "//div[@class='oxd-grid-3 orangehrm-full-width-grid']//div[@class='oxd-select-wrapper']//*[text()='Texas R&D']")
    private WebElement locationRD;

    @FindBy(css = ".oxd-switch-input")
    private WebElement checkBox;
@FindBy
        (css = ".oxd-grid-3 > div:nth-of-type(2) > .oxd-input-group > div:nth-of-type(2)")
        private WebElement jjoptitle;
//Select jjooptitle = new Select(driver.findElement(By.cssSelector(".oxd-grid-3 > div:nth-of-type(2) > .oxd-input-group > div:nth-of-type(2)")));
   // Select jobTitle = new Select(driver.findElement(By.xpath("//div[@class='oxd-grid-3 orangehrm-full-width-grid']//div[@class='oxd-select-text oxd-select-text--active']/div[.='Software Engineer']")));
@FindBy(xpath = "//*[@class='oxd-select-dropdown']")
private WebElement SelectSWEngineer;
//Select SelectSWEngineerzz = new Select(driver.findElement(By.xpath("//div[@class='oxd-select-dropdown']")));



    @FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[1]//input[@class='oxd-input oxd-input--active']")
            private WebElement joinDate;
    @FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[2]//input[@class='oxd-input oxd-input--active']")
    private WebElement endDate;

    @FindBy(css = ".oxd-button--secondary")
            private WebElement save;

    @FindBy(css = ".oxd-text--toast-message")
            private WebElement success;



    List<WebElement> fullList = driver.findElements(By.cssSelector(".oxd-grid-3 > div:nth-of-type(2) > .oxd-input-group > div:nth-of-type(2)"));
//Select Title = new Select(driver.findElement(By.cssSelector(".oxd-grid-3 > div:nth-of-type(2) > .oxd-input-group > div:nth-of-type(2)")));

    public void EditJopDetailsOld() throws InterruptedException {
        AddJoinDate1.sendKeys("2015-06-15");


        System.out.println(jjoptitle.getText());
        jjoptitle.click();
        System.out.println("print all  "+fullList);
        Object List = null;
        String CSS = String.valueOf(fullList.get(0).getText());
        System.out.println(CSS + "  printed nuber 8");



    }

    public  void EditJopDetails(){
        AddJoinDate1.sendKeys("2015-06-15");
        SelectJob1st.click();
        System.out.println(SelectJob1st.getTagName().getClass());
        SelectJob.click();

        jopType.click();
        jopTypepartTime.click();

        jobUnite.click();
        jobUniteassurance.click();

        location.click();
        locationRD.click();
        checkBox.click();
//        User user = new User().getDate()
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        LocalDate dt1 = LocalDate.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate result = dt1.withYear(1);
        String date2 = dt1.format(formatter1);


        joinDate.sendKeys(date);
        endDate.sendKeys("2024-12-05");

        save.click();
        success.isDisplayed();

    }
}

