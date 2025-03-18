package com.Foodics.Amazon.base.utils;

import io.restassured.http.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {
    public static List<org.openqa.selenium.Cookie> ConvertRestAssuredcookiestoSeliniumCookies(List<Cookie> RestAssuredCookies){

        List<org.openqa.selenium.Cookie> SeleniumCookies = new ArrayList<>();

        for(io.restassured.http.Cookie cookie : RestAssuredCookies){
            org.openqa.selenium.Cookie SeleniumCookie = new org.openqa.selenium.Cookie(cookie.getName(),cookie.getValue());

            SeleniumCookies.add(SeleniumCookie);
        }
        //inject cookies to browser in base test
        return SeleniumCookies;
    }
}
