package com.usman.restaurantBooking.repository;

import com.usman.restaurantBooking.entities.OrderBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBillRepository extends JpaRepository<OrderBill, Integer> {
}