package com.usman.restaurantBooking.repository;

import com.usman.restaurantBooking.entities.TableBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableBookingRepository extends JpaRepository<TableBooking, Integer> {
}