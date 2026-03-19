package com.devemersonc.controller;

import com.devemersonc.model.CreateUpdateProductDTO;
import com.devemersonc.model.PickDTO;
import com.devemersonc.model.ProductResponseDTO;
import com.devemersonc.model.ValidationErrorProductDTO;
import com.devemersonc.service.ProductService;

import java.util.List;

public class ProductController {
    private final ProductService productService = new ProductService();

    public List<ProductResponseDTO> loadAllProducts() throws Exception {
        return productService.getProducts();
    }

    public ProductResponseDTO loadProductBySku(String sku) throws Exception {
        return productService.getProductBySku(sku);
    }

    public ProductResponseDTO loadProductByName(String name) throws Exception {
        return productService.loadProductByName(name);
    }

    public ValidationErrorProductDTO updateProduct(Long product_id, CreateUpdateProductDTO createUpdateProductDTO) throws Exception{
        return productService.updateProduct(product_id, createUpdateProductDTO);
    }

    public ValidationErrorProductDTO createProduct(CreateUpdateProductDTO createUpdateProductDTO) throws Exception{
        return productService.createProduct(createUpdateProductDTO);
    }

    public ProductResponseDTO findByName(String name) {
        try {
            return productService.getProductByName(name);
        }catch (Exception e) {
            return null;
        }
    }

    public String pickProduct(Long productId, int quantity) {
        try {
            return productService.pickProduct(productId, quantity);
        }catch (Exception e) {
            return null;
        }
    }

    public void deleteProduct(Long product_id) throws Exception{
        productService.deleteProduct(product_id);
    }
}
