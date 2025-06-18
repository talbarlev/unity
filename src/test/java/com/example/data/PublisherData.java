package com.example.data;

public class PublisherData {
    private String name;
    private String email;
    private Integer count;

    public PublisherData(String name, String email, Integer count) {
        this.name = name;
        this.email = email;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getCount() {
        return count;
    }

    public static class Builder {
        private String name;
        private String email;
        private Integer count = -1;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder count(Integer count) {
            this.count = count;
            return this;
        }

        public PublisherData build() {
            return new PublisherData(name, email, count);
        }
    }
}
