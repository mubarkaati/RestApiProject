package com.Test.October07.controller;

import com.Test.October07.model.Restaurant;
import com.Test.October07.services.RestaurantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {
    @Autowired
    RestaurantDao restaurantDao;

    @PostMapping(value = "/registernew", consumes = "application/JSON")
    void addNewRestaurant(@RequestBody Restaurant restaurant) {
        restaurantDao.addNewRestaurant(restaurant);
    }

    @GetMapping("/getall")
    List<Restaurant> fetchAllRestaurant() {
        return restaurantDao.fetchAllRestaurant();
    }

    @DeleteMapping("/delete/{restaurantId}")
    void deleteRestaurant(int restaurantId) {
        restaurantDao.deleteRestaurant(restaurantId);
    }
}