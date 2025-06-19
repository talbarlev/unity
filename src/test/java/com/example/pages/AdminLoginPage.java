package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    protected void enterUsername(String username) {
        safeTypeText(usernameInput, username);
    }

    protected void enterPassword(String password) {
        safeTypeText(passwordInput, password);
    }

    protected void clickLogin() {
        safeClick(loginButton);
    }

    public void login(String username, String password) {
        this.enterUsername(username);
        this.enterPassword(password);
        this.clickLogin();
    }
}
