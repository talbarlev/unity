package com.example.data.publisher;

public class PublisherData {
    private final String id;
    private final String name;
    private final String email;

    private PublisherData(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private String id; // אפשרי או null
        private String name;
        private String email;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public PublisherData build() {
            return new PublisherData(this);
        }
    }
}
