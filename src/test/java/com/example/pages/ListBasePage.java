package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ListBasePage extends HomePage {

    protected String listSelector;
    protected By createNewButton = By.cssSelector("[data-testid='action-new']");

    public ListBasePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCreate() {
        WebElement createButton = driver.findElement(createNewButton);

        this.click(createButton);
    }

}
