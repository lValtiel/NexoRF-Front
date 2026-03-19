package com.devemersonc.service;

import com.devemersonc.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Alert;
import com.devemersonc.utils.AlertUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class UserService {
    private static final String BASE_URL = "http://localhost:8080/api/users";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    //Crear nuevo usuario
    public Map<String, String> createUser(RegisterUserRequest registerUserRequest) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(registerUserRequest)))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 400) {
            return gson.fromJson(response.body(), new TypeToken<Map<String, String>>(){}.getType());
        }else if(response.statusCode() == 409) {
            ErrorDTO error = gson.fromJson(response.body(), ErrorDTO.class);
            throw new RuntimeException(error.getMessage());
        }
        return null;
    }

    //Actualizar usuario
    public Map<String, String> updateUser(Long user_id, CreateUser userUpdateDTO) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + user_id))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(userUpdateDTO)))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 400) {
            return gson.fromJson(response.body(), new TypeToken<Map<String, String>>(){}.getType());
        }
        return null;
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

    //Eliminar usuario
    public void deleteUser(Long user_id) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + user_id))
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .DELETE()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
