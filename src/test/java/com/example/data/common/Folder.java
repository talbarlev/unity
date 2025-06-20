package com.example.data.common;

public enum Folder {
    PUBLISHER("Publisher"),
    POST("Post"),
    PROFILE("Profile");

    private final String label;

    Folder(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
