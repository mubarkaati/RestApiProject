package com.usman.restaurantBooking.repository;

import com.usman.restaurantBooking.entities.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMenuRepository extends JpaRepository<OrderMenu, Integer> {
}