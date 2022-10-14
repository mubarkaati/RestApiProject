package com.Test.October07.services;

import com.Test.October07.model.Restaurant;

import java.util.List;

public interface RestaurantDao {
    List<Restaurant> fetchAllRestaurant();
    void addNewRestaurant(Restaurant restaurant);
    void deleteRestaurant(int restaurantId);
}
