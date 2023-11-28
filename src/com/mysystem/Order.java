package com.mysystem;

public class Order {
    private String orderId;
    private String orderName;
    private double servicePrice;

    public Order(String orderId, String orderName, double servicePrice) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.servicePrice = servicePrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Order Name: " + orderName + ", Service Price: " + servicePrice;
    }
}
