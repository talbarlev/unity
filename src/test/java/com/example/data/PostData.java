package com.example.data;

public class PostData {
    private String title;
    private String content;
    private String status;
    private boolean published;
    private String publisher;

    public PostData(String title, String content, String status, boolean published, String publisher) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.published = published;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public boolean isPublished() {
        return published;
    }

    public String getPublisher() {
        return publisher;
    }

    public static class Builder {
        private String title;
        private String content;
        private String status;
        private boolean published = false;
        private String publisher;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder published(boolean published) {
            this.published = published;
            return this;
        }

        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public PostData build() {
            return new PostData(title, content, status, published, publisher);
        }
    }
}
