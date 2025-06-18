package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class CreateBasePage extends HomePage {

    protected By saveButton = By.cssSelector("[data-testid='button-save']");

    public CreateBasePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSave() {
        WebElement createButton = driver.findElement(saveButton);

        this.click(createButton);
    }

    public abstract void create(Object data);


}
