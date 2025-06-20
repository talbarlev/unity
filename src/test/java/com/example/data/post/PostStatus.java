package com.example.data.post;

public enum PostStatus {
    ACTIVE,
    REMOVED;

    @Override
    public String toString() {
        return name(); // מחזיר את השם כמו שהוא: "ACTIVE", "REMOVED"
    }
}