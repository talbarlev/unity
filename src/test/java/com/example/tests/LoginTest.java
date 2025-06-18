package com.example.tests;

import com.example.base.BaseTest;
import com.example.pages.AdminLoginPage;
import com.example.pages.LandingPage;
import com.example.pages.PublisherPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        var loginPage = new AdminLoginPage(driver);

        loginPage.goTo();

        loginPage.login("admin@example.com", "password");

        var landingPage = new LandingPage(driver);

        landingPage.sideNavebar().navigateToFolder("Publisher");

        var publisherPage = new PublisherPage(driver);

        publisherPage.clickOnCreate();

//        publishCreatePage.create(publisherName, email);
//
//        publishCreatePage.sideList.navigateToTab(post);
//
//        postListPage.clickOnCreate();
//
//        postCreatePage.createPost(title, content, ACTIVE, true, publisherName);


    }


}
