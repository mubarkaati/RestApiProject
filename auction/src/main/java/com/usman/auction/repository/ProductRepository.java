package com.usman.auction.repository;

import com.usman.auction.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select (count(p) > 0) from Product p where p.productName like ?1")
    boolean isNameAvailable(String productName);
}