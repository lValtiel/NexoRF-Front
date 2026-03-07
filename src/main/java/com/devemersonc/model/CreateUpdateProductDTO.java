package com.devemersonc.model;

public class CreateUpdateProductDTO {
    private String sku;
    private String name;
    private Integer amount;
    private String location;

    public CreateUpdateProductDTO(String sku, String name, Integer amount, String location) {
        this.sku = sku;
        this.name = name;
        this.amount = amount;
        this.location = location;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
