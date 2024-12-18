package com.example.thesis_app.auth.dto.response;

import com.example.thesis_app.enums.Role;

public class LoginResponseModel {
    private Role role;

    public LoginResponseModel() {
    }

    public LoginResponseModel(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
