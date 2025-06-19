package com.example.tests;

import com.example.base.BaseTest;
import com.example.data.JsonItemData;
import com.example.data.PostData;
import com.example.data.PublisherData;
import com.example.pages.*;
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

        var createPublisherPage = new FormPublisherPage(driver);

        createPublisherPage.create(publisherDataForm);

//        PublisherData uiData = publisherPage.getPublisherByName(publisherDataForm.getName());

        publisherPage.sideNavebar().navigateToFolder("Post");

        var postPage = new PostPage(driver);

        postPage.clickOnCreate();

        var createPostPage = new FormPostPage(driver);

        PostData postDataForm = new PostData.Builder()
                .title(DataGenerator.generateUniqueName("title"))
                .content(DataGenerator.generateUniqueEmail("content"))
                .status("ACTIVE")
                .published(true)
                .publisher(publisherDataForm.getEmail())
                .addJsonItem(new JsonItemData.Builder()
                        .number(DataGenerator.randonNumber(1, 10))
                        .string(DataGenerator.generateUniqueName("string"))
                        .bool(true)
                        .date(DataGenerator.generateTimestamp())
                .build()
         ).build();


        createPostPage.create(postDataForm);


//        assertEquals(uiData.getName(), publisherDataForm.getName(), "⚠️ Name mismatch between UI and input data");
//        assertEquals(uiData.getEmail(), publisherDataForm.getEmail(), "⚠️ Email mismatch between UI and input data");
    }


}
