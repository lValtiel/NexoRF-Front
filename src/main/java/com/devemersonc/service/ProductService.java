package com.devemersonc.service;

import com.devemersonc.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ProductService {
    private static final String BASE_URL = "http://localhost:8080/api/products";
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    //Obtener todos los productos
    public List<ProductResponseDTO> getProducts() throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), new TypeToken<List<ProductResponseDTO>>(){}.getType());
    }

    //Actualizar producto
    public ValidationErrorProductDTO updateProduct(Long product_id, CreateUpdateProductDTO createUpdateProductDTO) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + product_id))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(createUpdateProductDTO)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 400) {
            return gson.fromJson(response.body(), ValidationErrorProductDTO.class);
        }
        return null;
    }

    //crear nuevo producto
    public ValidationErrorProductDTO createProduct(CreateUpdateProductDTO createUpdateProductDTO) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(createUpdateProductDTO)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 400) {
            return gson.fromJson(response.body(), ValidationErrorProductDTO.class);
        }
        return null;
    }

    //Metodo search producto por sku/name/location
    public ProductResponseDTO getProductBySku(String data) throws Exception {
        String url = BASE_URL + "/search?data=" + data;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() == 404 || response.body().isBlank()) {
            return null;
        }
        return gson.fromJson(response.body(), ProductResponseDTO.class);
    }

    //Metodo buscar producto solo por nombre
    public ProductResponseDTO getProductByName(String name) throws Exception{
        String url = BASE_URL + "/findByName?name=" + name;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 404) {
            ErrorMessageDTO error = gson.fromJson(response.body(), ErrorMessageDTO.class);

        }
        return gson.fromJson(response.body(), ProductResponseDTO.class);
    }

    //Metodo modificar cantidad del producto en inventario
    public String pickProduct(Long productId, int quantity) throws Exception{
        PickDTO dto = new PickDTO(productId, quantity);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/pick"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + SessionManager.getToken())
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(dto)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}