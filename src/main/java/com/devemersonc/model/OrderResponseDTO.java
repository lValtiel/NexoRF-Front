package com.devemersonc.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {
    private Long order_id;
    private String numOrder;
    private LocalDateTime createdAt;
    private String state;
    private List<OrderLineResponseDTO> orderLines;

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(String numOrder) {
        this.numOrder = numOrder;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<OrderLineResponseDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineResponseDTO> orderLines) {
        this.orderLines = orderLines;
    }
}
