package com.devemersonc.controller;

import com.devemersonc.model.AuthResponse;
import com.devemersonc.model.SessionManager;
import com.devemersonc.service.AuthService;

public class AuthController {

    private final AuthService authService = new AuthService();

    public boolean login(String username, String password) {
        try {
            AuthResponse response = authService.login(username, password);
            SessionManager.setToken(response.getToken());
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
