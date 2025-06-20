package com.example.tests;

import com.example.apis.AuthUtills;
import com.example.apis.HTTPclients.PostClient;
import com.example.apis.HTTPclients.PublisherClient;
import com.example.data.post.PostData;
import com.example.data.post.PostStatus;
import com.example.data.publisher.PublisherData;
import com.example.factories.TestDataFactory;
import com.example.utills.CommonAPI;
import com.example.utills.DataGenerator;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class APItests {
    static String cookie;
    private String createdPostId;
    private String createdPublisherId;

    @BeforeTest
    public void setUp() {
        cookie = AuthUtills.getSessionCookie();
    }

    @Test
    public void testCreatePublisher() {
        String publisherName = DataGenerator.generateUniqueName("HaimSheliPublisherAPI");
        String publisherEmail = DataGenerator.generateUniqueEmail("haimiPublisherAPI");
        String postTitle = DataGenerator.generateUniqueName("HaimSheliPostAPI");
        String postContent = DataGenerator.generateUniqueName("HaimSheliPostAPIWOOOO");

        PublisherClient publisherClient = new PublisherClient(cookie);
        PostClient postClient = new PostClient(cookie);

        PublisherData createPublisherData = TestDataFactory.createPublisher(publisherName, publisherEmail);
        Response createPubliserResponse = publisherClient.createPublisher(createPublisherData);
        createPubliserResponse.then().statusCode(200);
        System.out.println("📦 Publisher Response: " + createPubliserResponse.asPrettyString());

        createdPublisherId = createPubliserResponse.path("record.params.id").toString();

        PostData createPostData = TestDataFactory.createPostData(postTitle, postContent, PostStatus.ACTIVE, true, createdPublisherId);
        Response createPostResponse = postClient.createNewPost(createPostData);
        createPostResponse.then().statusCode(200);
        System.out.println("📦 Post Create Response: " + createPostResponse.asPrettyString());

        createdPostId = createPostResponse.path("record.params.id").toString();

        PostData editPostData = TestDataFactory.createPostData(postTitle, postContent, PostStatus.REMOVED, true, createdPublisherId);
        Response editPostResponse = postClient.editPostById(editPostData, createdPostId);
        editPostResponse.then().statusCode(200);
        System.out.println("📦 Post Edit Response: " + editPostResponse.asPrettyString());

        Response getPostByIdAfterEditResponse = postClient.getPostById(createdPostId);
        getPostByIdAfterEditResponse.then().statusCode(200);

        assertEquals(editPostResponse.path("record.params.title"), editPostData.getTitle());
        assertEquals(createPostResponse.path("record.params.status"), "ACTIVE");
        assertEquals(editPostResponse.path("record.params.status"), "REMOVED");
    }


    @AfterTest
    public void deleteAllData() {
       CommonAPI.deletePost(createdPostId, createdPublisherId, cookie);
    }
}