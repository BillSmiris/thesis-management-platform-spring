package com.example.thesis_app.configuration.auth;

import java.security.Principal;

public class CustomPrincipal implements Principal {
    private String username;

    public CustomPrincipal(String username) {
        this.username = username;
    }

    @Override
    public String getName() {
        return username;
    }
}
