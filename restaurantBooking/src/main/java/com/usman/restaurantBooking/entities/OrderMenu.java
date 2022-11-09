package com.usman.restaurantBooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderMenuId;

    @Column(nullable = false)
    private int itemQuantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
//    @JoinColumn(name = "bookings")
    private TableBooking tableBookingMenu;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonIgnore
////    @JoinColumn(name = "booked_orders")
//    private Order bookedOrders;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "orderedMenu")
    @JsonIgnore
    private Menu menus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order bookedOrders;


    public OrderMenu(int itemQuantity, TableBooking tableBookingMenu, Order bookedOrders, Menu menus) {
        this.itemQuantity = itemQuantity;
        this.tableBookingMenu = tableBookingMenu;
        this.bookedOrders = bookedOrders;
        this.menus = menus;
    }
}