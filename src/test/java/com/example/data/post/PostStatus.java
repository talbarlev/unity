package com.example.data.post;

public enum PostStatus {
    ACTIVE,
    REMOVED;

    @Override
    public String toString() {
        return name();
    }
}