package com.kaushiknsanji.acviewmodel.model;


public class User {

    private final String id;

    public User(String loginId) {
        id = loginId;
    }

    public String getLoginId() {
        return id;
    }
}
