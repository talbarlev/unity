package com.example.pages.publishers;

import com.example.data.publisher.PublisherData;
import com.example.pages.common.FormBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormPublisherPage extends FormBasePage {

    private By nameInput = By.id("name");
    private By emailInput = By.id("email");
    private final By formLocator = By.cssSelector("form[data-css='Publisher-new-form']");

    public FormPublisherPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return this.formLocator;
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
        safeTypeText(nameInput, name);
    }

    protected void enterEmail(String email) {
        safeTypeText(emailInput, email);
    }


}
