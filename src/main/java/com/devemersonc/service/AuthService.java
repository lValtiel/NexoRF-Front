package com.devemersonc.service;

import com.devemersonc.model.AuthRequest;
import com.devemersonc.model.AuthResponse;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthService {
    private static final String LOGIN_URL = "http://localhost:8080/api/auth/login";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public AuthResponse login(String username, String password) throws Exception {
        AuthRequest authRequest = new AuthRequest(username, password);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(LOGIN_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(authRequest)))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return gson.fromJson(response.body(), AuthResponse.class);
    }
}
