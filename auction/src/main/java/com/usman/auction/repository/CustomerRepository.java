package com.usman.auction.repository;

import com.usman.auction.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select (count(c) > 0) from Customer c where c.phoneNumber in ?1")
    boolean isPhoneNumberAvailable(Long phoneNumber);
}