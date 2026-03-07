package com.devemersonc.model;

import java.util.List;

public class CreateOrderDTO {

    private String numOrder;
    private String state;
    private List<ProductQuantityDTO> products;

    public String getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(String numOrder) {
        this.numOrder = numOrder;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ProductQuantityDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductQuantityDTO> products) {
        this.products = products;
    }
}
