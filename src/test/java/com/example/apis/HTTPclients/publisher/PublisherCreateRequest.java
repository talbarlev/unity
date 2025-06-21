package com.example.apis.HTTPclients;


public class PublisherCreateRequest {
    private final String name;
    private final String email;

    public PublisherCreateRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}