package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminLoginPage extends BasePage {

    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("loginButton");

    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        driver.get("http://localhost:3000/admin/login");
    }

    public void enterUsername(String username) {
        WebElement usernameField = driver.findElement(usernameInput);
        typeText(usernameField, username); // מה־BasePage
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(passwordInput);
        typeText(passwordField, password);
    }

    public void clickLogin() {
        WebElement loginBtn = driver.findElement(loginButton);
        click(loginBtn);
    }
}
