package com.example.components;

import com.example.pages.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SideNavbar extends Base {

    private String folderTextLocator = "//a[div[text()='%s']]";
    private By happyFolderToggle = By.xpath("//div[text()='Happy Folder']/ancestor::a");
    private By happyFolderOptions = By.xpath("following-sibling::ul");

    public SideNavbar(WebDriver driver) {
        super(driver);
    }

    public void navigateToFolder(String folderName) {
        WebElement happyFolder = wait.until(ExpectedConditions.visibilityOfElementLocated(happyFolderToggle));

        List<WebElement> folderOptionsElements = happyFolder.findElements(happyFolderOptions);

        if (folderOptionsElements.isEmpty()) {
            happyFolder.click();

            wait.until(driver -> !happyFolder.findElements(happyFolderOptions).isEmpty());
        }

        WebElement targetFolder = this.getFolderElementByName(folderName);

        wait.until(ExpectedConditions.elementToBeClickable(targetFolder));

        targetFolder.click();
    }

    private WebElement getFolderElementByName(String name) {
        By folderOption = By.xpath(String.format(folderTextLocator, name));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(folderOption));

    }

}
