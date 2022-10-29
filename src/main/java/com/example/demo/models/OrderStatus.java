package com.example.demo.models;

public class OrderStatus {

    private int orderStatusId;

    private String status;

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderStatus [orderStatusId=" + orderStatusId + ", status=" + status + "]";
    }

}