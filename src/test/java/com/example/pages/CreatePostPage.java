package com.example.pages;

import com.example.data.PostData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreatePostPage extends CreateBasePage {
    private By titleInput = By.id("title");
    private By contentInput = By.id("content");
    private By statusSelect = By.id("status");
    private By publishedCheckbox = By.id("published");
    private By publisherInput = By.cssSelector("input[id*='react-select']"); // מותאם לקומפוננטת select דינמית


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

        this.clickOnSave();
    }

    private void enterTitle(String title) {
        typeText(driver.findElement(titleInput), title);
    }

    private void enterContent(String content) {
        typeText(driver.findElement(contentInput), content);
    }

    private void selectStatus(String status) {
        WebElement dropdown = driver.findElement(statusSelect);
        new Select(dropdown).selectByVisibleText(status);
    }

    private void setPublished(boolean published) {
        WebElement checkbox = driver.findElement(publishedCheckbox);
        if (checkbox.isSelected() != published) {
            checkbox.click();
        }
    }

    private void choosePublisher(String publisherName) {
        WebElement input = driver.findElement(publisherInput);
        input.sendKeys(publisherName);
        input.sendKeys(org.openqa.selenium.Keys.ARROW_DOWN);
        input.sendKeys(org.openqa.selenium.Keys.ENTER);
    }
}


