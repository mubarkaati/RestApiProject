package com.usman.restaurantBooking.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serialNumber;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private float itemPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menus")
    private List<OrderMenu> orderMenus;
}