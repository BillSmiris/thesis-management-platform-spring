package com.example.thesis_app.auth.dto.request;

public class LoginRequestModel {
    private String username;
    private String password;

    public LoginRequestModel() {
    }

    public LoginRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}