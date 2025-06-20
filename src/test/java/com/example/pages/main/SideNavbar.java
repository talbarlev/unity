package com.example.pages.main;

import com.example.data.common.Folder;

import com.example.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SideNavbar extends BasePage {

    private String folderTextLocator = "//a[div[text()='%s']]";
    private By happyFolderToggle = By.xpath("//div[text()='Happy Folder']/ancestor::a");
    private By happyFolderOptions = By.xpath("following-sibling::ul");
    private By foldersLcoator = By.cssSelector("div[data-testid='sidebar-folder-list']");

    @Override
    protected By getPageIdentifier() {
        return foldersLcoator;
    }

    public SideNavbar(WebDriver driver) {
        super(driver);
    }

    public void navigateToFolder(Folder folder) {
        WebElement happyFolder = wait.until(ExpectedConditions.visibilityOfElementLocated(happyFolderToggle));

        List<WebElement> folderOptionsElements = happyFolder.findElements(happyFolderOptions);

        if (folderOptionsElements.isEmpty()) {
            happyFolder.click();

            wait.until(driver -> !happyFolder.findElements(happyFolderOptions).isEmpty());
        }

        WebElement targetFolder = this.getFolderElementByName(folder.getLabel());

        wait.until(ExpectedConditions.elementToBeClickable(targetFolder));

        targetFolder.click();
    }

    private WebElement getFolderElementByName(String name) {
        By folderOption = By.xpath(String.format(folderTextLocator, name));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(folderOption));
    }
}
