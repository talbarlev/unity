package com.example.pages.common;

import com.example.utills.TestLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Abstract base class for all Page Objects.
 * Provides reusable and safe interaction methods for Selenium tests.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    /** 🧠 Utility: Wait for visibility */
    protected WebElement waitForVisibility(By locator) {
        TestLogger.step("⏳ Waiting for visibility of element: " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** 🧠 Utility: Wait for clickability */
    protected WebElement waitForClickability(By locator) {
        TestLogger.step("⏳ Waiting for clickability of element: " + locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /** 📝 Types text with retry */
    protected void safeTypeText(By locator, String text) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                TestLogger.step("⌨️ Typing text into: " + locator + " => '" + text + "'");
                WebElement element = waitForVisibility(locator);
                element.clear();
                element.sendKeys(text);
                return;
            } catch (StaleElementReferenceException e) {
                TestLogger.warning("🔁 Stale during typing. Attempt #" + (attempts + 1));
                attempts++;
            }
        }
        TestLogger.error("❌ Failed to type text after 3 attempts: " + locator);
        throw new RuntimeException("❌ Failed to type text after 3 attempts: " + locator);
    }

    /** 👆 Safe click with retry and JS fallback */
    protected void safeClick(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                TestLogger.step("🖱️ Clicking on element: " + locator);
                WebElement element = waitForClickability(locator);
                element.click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                TestLogger.warning("🔁 Click failed. Attempt #" + (attempts + 1));
                attempts++;
            } catch (Exception e) {
                TestLogger.warning("⚠️ Unexpected click error: " + e.getMessage());
                break;
            }
        }

        // JavaScript fallback
        try {
            TestLogger.warning("🧪 Falling back to JS click on: " + locator);
            WebElement fallback = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fallback);
        } catch (Exception jsEx) {
            TestLogger.error("❌ JS click failed on: " + locator);
            throw new RuntimeException("❌ JS click failed on: " + locator, jsEx);
        }
    }

    /** 👆 Safe click on element */
    protected void safeClick(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                TestLogger.step("🖱️ Clicking on WebElement instance");
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                TestLogger.warning("🔁 Element click failed. Attempt #" + (attempts + 1));
                attempts++;
            } catch (Exception e) {
                TestLogger.warning("⚠️ Unexpected click error: " + e.getMessage());
                break;
            }
        }

        // JS fallback
        try {
            TestLogger.warning("🧪 Falling back to JS click on WebElement instance");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception jsEx) {
            TestLogger.error("❌ JS click failed on WebElement");
            throw new RuntimeException("❌ JS click failed on WebElement", jsEx);
        }
    }

    /** 🧾 Gets text from element by locator */
    protected String safeGetText(By locator) {
        try {
            TestLogger.step("🔍 Getting text from: " + locator);
            return waitForVisibility(locator).getText();
        } catch (StaleElementReferenceException e) {
            TestLogger.warning("⚠️ Retrying stale text read from: " + locator);
            return waitForVisibility(locator).getText();
        }
    }

    /** 🧾 Gets text from WebElement */
    protected String safeGetText(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.getText();
        } catch (StaleElementReferenceException e) {
            TestLogger.error("❌ Element is stale. Need locator for recovery.");
            throw new RuntimeException("❌ Element is stale. Need locator for recovery.");
        }
    }

    /** 🔽 Select React-style dropdown by label */
    protected void selectReactOptionByLabel(String labelText, String optionText) {
        String dropdownXpath = String.format("//label[text()='%s']/following::div[contains(@class,'control')][1]", labelText);
        TestLogger.step("📂 Selecting dropdown label: " + labelText);
        WebElement dropdown = waitForClickability(By.xpath(dropdownXpath));
        dropdown.click();

        String optionXpath = String.format("//div[contains(@class,'option') and text()='%s']", optionText);
        TestLogger.step("✅ Selecting option: " + optionText);
        WebElement option = waitForClickability(By.xpath(optionXpath));
        option.click();
    }

    /** 🖱 Hover over element */
    protected void hoverOnElement(WebElement element) {
        TestLogger.step("🖱 Hovering over element");
        actions.moveToElement(element).perform();
    }

    /** 👁️ Check if element is displayed */
    protected boolean isDisplayed(By locator) {
        try {
            boolean displayed = driver.findElement(locator).isDisplayed();
            TestLogger.step("👁️ Element visibility: " + locator + " = " + displayed);
            return displayed;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            TestLogger.warning("⚠️ Element not displayed or stale: " + locator);
            return false;
        }
    }
}
