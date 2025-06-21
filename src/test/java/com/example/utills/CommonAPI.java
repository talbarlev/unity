package com.example.utills;

import com.example.apis.HTTPclients.post.PostClient;
import com.example.apis.HTTPclients.publisher.PublisherClient;

public class CommonAPI {
    private CommonAPI() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void deletePublisherAndPost(String postId, String publisherId, String cookie) {
        if (cookie == null || postId == null || publisherId == null) {
            System.out.println("⚠️ Not all data was created successfully. Skipping deletion.");
            return;
        }

        PostClient postClient = new PostClient(cookie);
        PublisherClient publisherClient = new PublisherClient(cookie);

        postClient.deletePostById(postId);
        publisherClient.deletePublisherById(publisherId);
    }
}