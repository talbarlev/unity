package com.example.pages.common;

import com.example.pages.main.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class FormBasePage<T> extends HomePage {

    protected By saveButton = By.cssSelector("[data-testid='button-save']");

    public FormBasePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSave() {
        safeClick(saveButton);
    }

    public abstract void create(T data);
}