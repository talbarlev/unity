package com.example.utills;

import com.example.pages.AdminLoginPage;
import com.example.pages.main.LandingPage;
import org.openqa.selenium.WebDriver;

public class CommonUI {
    public static void login(WebDriver driver, String username, String password) {
     var loginPage = new AdminLoginPage(driver);
        loginPage.goTo();
        loginPage.login(username, password);
    }
}
