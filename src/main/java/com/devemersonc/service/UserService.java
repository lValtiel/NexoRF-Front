package com.devemersonc.service;

import com.devemersonc.model.CreateUser;
import com.devemersonc.model.RegisterUserRequest;
import com.devemersonc.model.SessionManager;
import com.devemersonc.model.UserResponseDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UserService {
    private static final String BASE_URL = "http://localhost:8080/api/users";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    //Crear nuevo usuario
    public void createUser(RegisterUserRequest registerUserRequest) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(registerUserRequest)))
                .build();
        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    //Obtener lista de usuarios
    public List<UserResponseDTO> getUsers() throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), new TypeToken<List<UserResponseDTO>>(){}.getType());
    }

    //Actualizar usuario
    public void updateUser(Long user_id, CreateUser userUpdateDTO) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + user_id))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(userUpdateDTO)))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
