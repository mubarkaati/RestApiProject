package com.usman.auction.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int auctionId;

    @Column(nullable = false)
    private float bidPrice;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product")
    @JsonIgnore
    private Product product;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer")
    @JsonIgnore
    private Customer customer;
}