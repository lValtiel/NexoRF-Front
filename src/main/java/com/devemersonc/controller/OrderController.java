package com.devemersonc.controller;

import com.devemersonc.service.OrderService;
import com.devemersonc.model.OrderResponseDTO;
import java.util.List;

public class OrderController {
    private final OrderService orderservice = new OrderService();

    public List<OrderResponseDTO> getOrders() {
        try {
            return orderservice.getOrders();
        }catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
