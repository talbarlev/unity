package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class FormBasePage extends HomePage {

    protected By saveButton = By.cssSelector("[data-testid='button-save']");

    public FormBasePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSave() {
        safeClick(saveButton);
    }

    public abstract void create(Object data);


}
