package com.usman.auction.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vendorId;

    @Column(nullable = false)
    private String vendorName;

    @Column(nullable = false)
    private Long phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor")
    private List<Product> products;
}