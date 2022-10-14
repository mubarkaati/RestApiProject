package com.Test.October07.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dish {
    @Id
    private int dishId;
    private String dishName;
    private float dishPrice;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public float getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(float dishPrice) {
        this.dishPrice = dishPrice;
    }
}
