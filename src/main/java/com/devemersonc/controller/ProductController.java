package com.devemersonc.controller;

import com.devemersonc.model.CreateUpdateProductDTO;
import com.devemersonc.model.ProductResponseDTO;
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

    public void updateProduct(Long product_id, CreateUpdateProductDTO createUpdateProductDTO) throws Exception{
        productService.updateProduct(product_id, createUpdateProductDTO);
    }

    public void createProduct(CreateUpdateProductDTO createUpdateProductDTO) throws Exception{
        productService.createProduct(createUpdateProductDTO);
    }
}
