package com.example.data;

public class JsonItemData {
    private Integer number;
    private String string;
    private Boolean bool;
    private String date;

    public JsonItemData(Integer number, String string, Boolean bool, String date) {
        this.number = number;
        this.string = string;
        this.bool = bool;
        this.date = date;
    }

    public Integer getNumber() {
        return number;
    }

    public String getString() {
        return string;
    }

    public Boolean getBool() {
        return bool;
    }

    public String getDate() {
        return date;
    }

    public static class Builder {
        private Integer number = 0;
        private String string = "";
        private Boolean bool = false;
        private String date = "2000-01-01T00:00";

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public Builder string(String string) {
            this.string = string;
            return this;
        }

        public Builder bool(Boolean bool) {
            this.bool = bool;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public JsonItemData build() {
            return new JsonItemData(number, string, bool, date);
        }
    }
}
