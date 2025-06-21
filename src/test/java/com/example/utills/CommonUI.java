package com.example.utills;

import com.example.pages.AdminLoginPage;
import org.openqa.selenium.WebDriver;

public class CommonUI {

    private CommonUI() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void login(WebDriver driver, String username, String password) {
        var loginPage = new AdminLoginPage(driver);
        loginPage.goTo();
        loginPage.login(username, password);
    }
}