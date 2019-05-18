package com.example.cly.word.common;

public class User {
    private String userId;
    private String name;
    private String password;
    private USER_TYPE type;

    public User(String userId, String name, String password, USER_TYPE type) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public USER_TYPE getType() {
        return type;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(USER_TYPE type) {
        this.type = type;
    }
}


