package com.example.data.publisher;

public class PublisherData {
    private String name;
    private String email;

    public PublisherData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private String name;
        private String email;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public PublisherData build() {
            return new PublisherData(name, email);

        }
    }
}
