package com.example.pages;

import com.example.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage extends BasePage {

    private By usernameInput = By.name("email");
    private By passwordInput = By.name("password");
    private By loginButton = By.xpath("//button[text()='Login']");

    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        driver.get("http://localhost:3000/admin/login");
    }

    public void login(String username, String password) {
        this.enterUsername(username);
        this.enterPassword(password);
        this.clickLogin();
    }

    protected void enterUsername(String username) {
        safeTypeText(usernameInput, username);
    }

    protected void enterPassword(String password) {
        safeTypeText(passwordInput, password);
    }

    protected void clickLogin() {
        safeClick(loginButton);
    }
}