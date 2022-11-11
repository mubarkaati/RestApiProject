package com.usman.auction.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    @Column(nullable = false)
    private String productName;

    @ColumnDefault("0")
    private boolean isSold;

    @ColumnDefault("-1")
    private float soldPrice;

    private float basePrice;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vendor")
    @JsonIgnore
    private Vendor vendor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Auction> auctions;
}