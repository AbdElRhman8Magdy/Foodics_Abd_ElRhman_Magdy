package com.Foodics.Amazon.testcases;

import com.Foodics.Amazon.API.ReqresApi;
import com.Foodics.Amazon.base.BaseTest;
import com.Foodics.Amazon.pages.CartDetails;
import com.Foodics.Amazon.pages.LoginPage;
import com.Foodics.Amazon.pages.VideoGames;
import org.testng.annotations.Test;

import java.io.IOException;

public class AmazonTaskTest extends BaseTest {





    @Test
    public void API() throws InterruptedException, IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.Load()
                .login()
                .OpenAllMenu()
                .Filter()
        ;
        VideoGames videoGames = new VideoGames(getDriver());
        videoGames.addProductsBelowPriceToCart(15000);
//                .NavigateToEmpPage();
        CartDetails cartDetails = new CartDetails(getDriver());
        cartDetails.ViewCart();


    }
    @Test
    public void APis(){
        ReqresApi reqresApiTests = new ReqresApi();
        reqresApiTests.createUser();
        reqresApiTests.retrieveUser();
        reqresApiTests.updateUser();
    }

}
