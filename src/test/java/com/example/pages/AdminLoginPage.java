package com.example.pages;

import com.example.data.common.Properties;
import com.example.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage extends BasePage {

    private By usernameInput = By.name("email");
    private By passwordInput = By.name("password");
    private By loginButton = By.xpath("//button[text()='Login']");
    private final By loginForm = By.xpath("//input[@name='email' and @placeholder='Email']");

    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() { // TODO : better locator
        return this.loginForm;
    }

    public void goTo() {
        driver.get(Properties.BASEURL);
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

        // WAIT FOR DISAPPEAR
    }
}
