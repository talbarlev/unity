package com.example.pages;

import com.example.data.JsonItemData;
import com.example.data.PostData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CreatePostPage extends CreateBasePage {
    private By titleInput = By.id("title");
    private By contentInput = By.id("content");
    private By publishedCheckbox = By.id("published");
    private By addJSON = By.cssSelector("[data-testid='someJson-add']");
    private By jsonNumber = By.id("someJson.0.number");
    private By jsonString = By.id("someJson.0.string");
    private By jsonBoolean = By.id("someJson.0.boolean");
    private By jsonDate = By.id("someJson.0.array");

    public CreatePostPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void create(Object data) {
        if (!(data instanceof PostData)) {
            throw new IllegalArgumentException("Expected PostData");
        }

        PostData postData = (PostData) data;

        enterTitle(postData.getTitle());
        enterContent(postData.getContent());
        selectStatus(postData.getStatus());
        setPublished(postData.isPublished());
        choosePublisher(postData.getPublisher());

        // TODO : seprate
        clickAddJson();

        JsonItemData jsonItemData = postData.getJsonItems().get(0);

        setJsonString(jsonItemData.getString());
        setJsonNumber(jsonItemData.getNumber());
        setJsonBoolean(jsonItemData.getBool());
        setJsonDate(jsonItemData.getDate());

        this.clickOnSave();
    }


    private void enterTitle(String title) {
        typeText(driver.findElement(titleInput), title);
    }

    private void enterContent(String content) {
        typeText(driver.findElement(contentInput), content);
    }

    private void selectStatus(String status) {
        selectReactOptionByLabel("Status", status);
    }

    // Validate already checked
    private void setPublished(boolean toPublished) {
        if (toPublished) {
            click(driver.findElement(publishedCheckbox));
        }
    }

    private void choosePublisher(String publisherName) {
        selectReactOptionByLabel("Publisher", publisherName);
    }

    private void clickAddJson() {
        click(driver.findElement(addJSON));
    }

    private void setJsonNumber(int number) {
        WebElement numberFieldElement = driver.findElement(jsonNumber);
        this.typeText(numberFieldElement, String.valueOf(number));
    }

    private void setJsonString(String string) {
        WebElement stringFieldElement = driver.findElement(jsonString);
        this.typeText(stringFieldElement, string);
    }

    private void setJsonBoolean(boolean bool) {
        WebElement booleanCheckboxElement = driver.findElement(jsonString);

        this.click(booleanCheckboxElement);
    }

    private void setJsonDate(String date) {
        WebElement dateFieldElement = driver.findElement(jsonDate);

        this.typeText(dateFieldElement, date);
    }
}


