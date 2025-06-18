package com.example.tests;

import com.example.base.BaseTest;
import com.example.data.PublisherData;
import com.example.pages.AdminLoginPage;
import com.example.pages.CreatePublisherPage;
import com.example.pages.LandingPage;
import com.example.pages.PublisherPage;
import com.example.utills.DataGenerator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest()  {
        var loginPage = new AdminLoginPage(driver);

        loginPage.goTo();

        loginPage.login("admin@example.com", "password");

        var landingPage = new LandingPage(driver);

        landingPage.sideNavebar().navigateToFolder("Publisher");

        var publisherPage = new PublisherPage(driver);

        publisherPage.clickOnCreate();

        PublisherData publisherDataForm = new PublisherData.Builder()
                .name(DataGenerator.generateUniqueName("HaimSheli"))
                .email(DataGenerator.generateUniqueEmail("haimi"))
                .build();

        var createPublisherPage = new CreatePublisherPage(driver);

        createPublisherPage.create(publisherDataForm);

        PublisherData uiData = publisherPage.getPublisherByName(publisherDataForm.getName());

//        publishCreatePage.sideList.navigateToTab(post);
//
//        postListPage.clickOnCreate();
//
//        postCreatePage.createPost(title, content, ACTIVE, true, publisherName);

        assertEquals(uiData.getName(), publisherDataForm.getName(), "⚠️ Name mismatch between UI and input data");
        assertEquals(uiData.getEmail(), publisherDataForm.getEmail(), "⚠️ Email mismatch between UI and input data");
    }


}
