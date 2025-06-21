package com.example.pages.publishers;

import com.example.data.publisher.PublisherData;
import com.example.pages.common.FormBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormPublisherPage extends FormBasePage<PublisherData> {

    private By nameInput = By.id("name");
    private By emailInput = By.id("email");

    public FormPublisherPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void create(PublisherData data) {
        this.enterName(data.getName());
        this.enterEmail(data.getEmail());
        this.clickOnSave();
    }

    protected void enterName(String name) {
        safeTypeText(nameInput, name);
    }

    protected void enterEmail(String email) {
        safeTypeText(emailInput, email);
    }

}
