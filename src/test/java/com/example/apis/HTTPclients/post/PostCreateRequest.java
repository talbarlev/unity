package com.example.apis.HTTPclients.post;

import com.example.data.post.JsonItemData;
import com.example.data.post.PostStatus;

import java.util.List;

public class PostCreateRequest {
    private String title;
    private String content;
    private PostStatus status;
    private boolean published;
    private String publisherId;
    private List<JsonItemData> jsonItems;

    public PostCreateRequest(String title, String content, PostStatus status, boolean published, String publisherId, List<JsonItemData> jsonItems) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.published = published;
        this.publisherId = publisherId;
        this.jsonItems = jsonItems;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public PostStatus getStatus() { return status; }
    public boolean isPublished() { return published; }
    public String getPublisher() { return publisherId; }
    public List<JsonItemData> getJsonItems() { return jsonItems; }
}
