package com.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


// TODO : check whats still relvant

public abstract class BasePage extends Base {
    public BasePage(WebDriver driver) {
        super(driver);
    }

    protected void click(WebElement element) {

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            element.click();

        } catch (Exception e) {
            System.out.println("⚠️ Regular click failed, falling back to JS click: " + e.getMessage());

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }


    protected void typeText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
}