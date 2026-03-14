package com.devemersonc.model;

public class SessionManager {
    private static String jwtToken;
    private static OrderResponseDTO currentOrder;

    public static void setToken(String token) {
        jwtToken = token;
    }

    public static String getToken() {
        return jwtToken;
    }

    public static void setCurrentOrder(OrderResponseDTO order) {
        currentOrder = order;
    }

    public static OrderResponseDTO getCurrentOrder() {
        return currentOrder;
    }
}
