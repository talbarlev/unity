package com.example.pages;

import com.example.data.PublisherData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreatePublisherPage extends CreateBasePage {

    private By nameInput = By.id("name");
    private By emailInput = By.id("email");



    public CreatePublisherPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void create(Object data) {
        if (!(data instanceof PublisherData)) {
            throw new IllegalArgumentException("Expected PublisherData");
        }

        PublisherData publisherData = (PublisherData) data;

        this.enterName(publisherData.getName());
        this.enterEmail(publisherData.getEmail());
        this.clickOnSave();
    }

    protected void enterName(String name) {
        WebElement nameFieldElement = driver.findElement(nameInput);
        this.typeText(nameFieldElement, name);
    }

    protected void enterEmail(String email) {
        WebElement emailFieldElement = driver.findElement(emailInput);
        this.typeText(emailFieldElement, email);
    }


}
