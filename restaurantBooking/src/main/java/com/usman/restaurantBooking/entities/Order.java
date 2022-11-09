package com.usman.restaurantBooking.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "order_details")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @OneToMany(mappedBy = "bookedOrders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMenu> orderMenus = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "table_booking_id")
    private TableBooking tableBooking;

}
