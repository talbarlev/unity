package com.example.tests;

import com.example.base.BaseTest;
import com.example.data.common.Folder;
import com.example.data.post.JsonItemData;
import com.example.data.post.PostData;
import com.example.data.common.Properties;
import com.example.data.post.PostStatus;
import com.example.data.publisher.PublisherData;
import com.example.factories.TestDataFactory;
import com.example.pages.main.LandingPage;
import com.example.pages.posts.FormPostPage;
import com.example.pages.posts.PostPage;
import com.example.pages.publishers.FormPublisherPage;
import com.example.pages.publishers.PublisherPage;
import com.example.utills.CommomAPI;
import com.example.utills.CommonUI;
import com.example.utills.DataGenerator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static com.example.tests.APItests.cookie;
import static org.testng.Assert.assertEquals;

public class PostTests extends BaseTest {

//    static String cookie;
//    private String createdPostId;
//    private String createdPublisherId;

    @Test
    public void createPostWithPublisherAndEditStatus() {
        String publisherName = DataGenerator.generateUniqueName("HaimSheliPublisherUI");
        String publisherEmail = DataGenerator.generateUniqueEmail("haimiPublisherUI");

        String postTitle = DataGenerator.generateUniqueName("HaimSheliPostUI");
        String postContent = DataGenerator.generateUniqueName("HaimSheliPostUIWOOOO");

        CommonUI.login(driver, Properties.ADMIN_USERNAME, Properties.ADMIN_PASSWORD);

        var landingPage = new LandingPage(driver);
        landingPage.sideNavebar().navigateToFolder(Folder.PUBLISHER);

        var publisherPage = new PublisherPage(driver);
        publisherPage.clickOnCreate();

        PublisherData publisherDataForm = TestDataFactory.createPublisher(publisherName, publisherEmail);

        var createPublisherPage = new FormPublisherPage(driver);
        createPublisherPage.create(publisherDataForm);

        publisherPage.sideNavebar().navigateToFolder(Folder.POST);

        var postPage = new PostPage(driver);
        postPage.clickOnCreate();

        PostData postDataForm = TestDataFactory.createPostData(postTitle, postContent, PostStatus.ACTIVE, true, publisherEmail);

        var createPostPage = new FormPostPage(driver);
        createPostPage.create(postDataForm);

        PostData postDataAfterCreation = postPage.getPostByTitle(postDataForm.getTitle());
        String statusAfterCreate = postDataAfterCreation.getStatus();

        postPage.clickOnEditInRow(postDataForm.getTitle());

        var editPostPage = new FormPostPage(driver);

        editPostPage.selectStatus(PostStatus.REMOVED.toString());
        editPostPage.clickOnSave();

        PostData postDataAfterEdit = postPage.getPostByTitle(postDataForm.getTitle());
        String statusAfterEdit = postDataAfterEdit.getStatus();

        //  assertEquals(postDataForm.getStatus(), statusAfterCreate);
        //assertEquals(PostStatus.REMOVED, statusAfterEdit);
    }

//    @AfterTest
//    public void deleteAllData() {
//            CommomAPI.deletePost(createdPostId, createdPublisherId, cookie);
//    }

}