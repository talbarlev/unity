package com.example.pages.common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Abstract base class for all Page Objects.
 * Provides reusable and safe interaction methods for Selenium tests.
 */
public abstract class BasePage extends Base {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Types text into an element located by a given locator,
     * retrying if the element becomes stale.
     */
    protected void safeTypeText(By locator, String text) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                element.clear();
                element.sendKeys(text);
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println("🔁 Element went stale during typing. Retrying... Attempt #" + (attempts + 1));
                attempts++;
            }
        }
        throw new RuntimeException("Failed to type after 3 attempts due to stale element: " + locator);
    }
    /**
     * Safely clicks an element found by a locator, with retry and fallback to JS click.
     */
    protected void safeClick(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                element.click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                System.out.println("🔁 Click failed. Retrying... Attempt #" + (attempts + 1));
                attempts++;
            } catch (Exception e) {
                System.out.println("⚠️ Unexpected click failure: " + e.getMessage());
                break;
            }
        }

        try {
            WebElement fallbackElement = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fallbackElement);
        } catch (Exception jsException) {
            throw new RuntimeException("Failed to click element: " + locator, jsException);
        }
    }

    /**
     * Safely clicks an already located WebElement, with retry and fallback to JS click.
     */
    protected void safeClick(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                System.out.println("🔁 Element click failed. Retrying... Attempt #" + (attempts + 1));
                attempts++;
            } catch (Exception e) {
                System.out.println("⚠️ Unexpected click failure: " + e.getMessage());
                break;
            }
        }

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception jsException) {
            throw new RuntimeException("Failed to click element: " + element, jsException);
        }
    }


    /**
     * Returns the text of an element located by a given locator.
     */
    protected String safeGetText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText();
        } catch (StaleElementReferenceException e) {
            System.out.println("⚠️ Element went stale during getText. Retrying once...");
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText();
        }
    }

    /**
     * Selects an option from a React-style dropdown based on its label and option text.
     * Assumes dropdowns are built using divs with custom classNames.
     */
    protected void selectReactOptionByLabel(String labelText, String optionText) {
        String dropdownXpath = String.format("//label[text()='%s']/following::div[contains(@class,'control')][1]", labelText);
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXpath)));
        dropdown.click();

        String optionXpath = String.format("//div[contains(@class,'option') and text()='%s']", optionText);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
        option.click();
    }

    /**
     * Performs a mouse hover over the element located by the given locator.
     */
    protected void hoverOnElement(WebElement element) {
        actions.moveToElement(element).perform();
    }
}
