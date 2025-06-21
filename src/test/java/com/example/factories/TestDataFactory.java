package com.example.factories;

import com.example.data.post.JsonItemData;
import com.example.data.post.PostData;
import com.example.data.post.PostStatus;
import com.example.data.publisher.PublisherData;
import com.example.utills.DataGenerator;

public class TestDataFactory {

    public static PublisherData createPublisher(String name, String email) {
        return new PublisherData.Builder()
                .name(name)
                .email(email)
                .build();
    }


    public static PostData createPostData(String title, String content, PostStatus status,
                                          boolean published, String publisherIdOrEmail) {
        return new PostData.Builder()
                .title(title)
                .content(content)
                .status(status.toString())
                .published(published)
                .publisher(publisherIdOrEmail)
                .addJsonItem(createJsonItemData())
                .build();
    }

    public static JsonItemData createJsonItemData() {
        return new JsonItemData.Builder()
                .number(DataGenerator.randonNumber(1, 10))
                .string(DataGenerator.generateUniqueName("string"))
                .bool(true)
                .date(DataGenerator.generateTimestamp())
                .build();
    }
}
