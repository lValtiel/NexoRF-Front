package com.devemersonc.model;

public class OrderStateDTO {
    private Long orderId;
    private String state;

    public OrderStateDTO(Long orderId, String state) {
        this.orderId = orderId;
        this.state = state;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
