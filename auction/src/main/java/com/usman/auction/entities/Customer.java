package com.usman.auction.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private Long phoneNumber;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer")
    private List<Auction> auctions;
}