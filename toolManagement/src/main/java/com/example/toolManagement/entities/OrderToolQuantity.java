package com.example.toolManagement.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_tool")
public class OrderToolQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderToolId;
    private Long orderId;
    private Long toolId;
    private int toolQuantity;

    public int getOrderToolId() {
        return orderToolId;
    }

    public void setOrderToolId(int orderToolId) {
        this.orderToolId = orderToolId;
    }

    public int getToolQuantity() {
        return toolQuantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }


    public void setToolQuantity(int toolQuantity) {
        this.toolQuantity = toolQuantity;
    }

    @Override
    public String toString() {
        return "OrderTool{" +
                "orderToolId=" + orderToolId +
                ", orderId=" + orderId +
                ", toolId=" + toolId +
                ", toolQuantity=" + toolQuantity +
                '}';
    }
}