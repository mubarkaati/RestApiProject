package com.Test.October07.services;

import com.Test.October07.model.Dish;
import com.Test.October07.model.Restaurant;
import com.Test.October07.utils.GetSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantDaoImplementation implements RestaurantDao {
    @Override
    public List<Restaurant> fetchAllRestaurant() {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("From Restaurant");
        return query.list();
    }

    @Override
    public void addNewRestaurant(Restaurant restaurant) {
        List<Dish> dishes = restaurant.getDishes();
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(restaurant);
        dishes.stream().forEach(dish -> session.save(dish));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        Restaurant restaurant = session.get(Restaurant.class, restaurantId);
        List<Dish> dishes = restaurant.getDishes();
        session.beginTransaction();
        dishes.stream().forEach(dish -> session.delete(dish));
        session.getTransaction().commit();
        session.close();
    }
}