package com.usman.auction.repository;

import com.usman.auction.entities.Auction;
import com.usman.auction.entities.Customer;
import com.usman.auction.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {
    @Query("select a from Auction a where a.product in ?1 order by a.bidPrice")
    List<Auction> maxAuction(Product product);

    Auction findByCustomer(Customer byId);
}