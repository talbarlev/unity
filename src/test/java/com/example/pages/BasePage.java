package com.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


// TODO : check whats still relvant

public abstract class BasePage extends Base {

    public BasePage(WebDriver driver) {
        super(driver);
    }

//    protected void waitForPageToLoad() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(PageLocator));
//    }

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

    // TODO : if more selects are apears put the String in a desagnated file
    protected void selectReactOptionByLabel(String labelText, String optionText) {
        String dropdownXpath = String.format("//label[text()='%s']/following::div[contains(@class,'control')][1]", labelText);
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXpath)));
        dropdown.click();

        String optionXpath = String.format("//div[contains(@class,'option') and text()='%s']", optionText);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
        option.click();
    }
}