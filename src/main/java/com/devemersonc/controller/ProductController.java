package com.devemersonc.controller;

import com.devemersonc.model.CreateUpdateProductDTO;
import com.devemersonc.model.ProductResponseDTO;
import com.devemersonc.model.ValidationErrorProductDTO;
import com.devemersonc.service.ProductService;

import java.util.List;

public class ProductController {
    private final ProductService productService = new ProductService();

    public List<ProductResponseDTO> loadAllProducts() throws Exception {
        return productService.getProducts();
    }

    public ProductResponseDTO loadProductBySku(String data) throws Exception {
        return productService.getProductBySku(data);
    }

    public ValidationErrorProductDTO updateProduct(Long product_id, CreateUpdateProductDTO createUpdateProductDTO) throws Exception{
        return productService.updateProduct(product_id, createUpdateProductDTO);
    }

    public ValidationErrorProductDTO createProduct(CreateUpdateProductDTO createUpdateProductDTO) throws Exception{
        return productService.createProduct(createUpdateProductDTO);
    }
}
