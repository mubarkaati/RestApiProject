package com.usman.restaurantBooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "table_booking")
public class TableBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Integer tableId;

    @Column(nullable = false)
    private String personName;

    @Column(nullable = false)
    private int numberOfPersons;

    @Column(nullable = false)
    private long phoneNumber;

    @ColumnDefault("false")
    private boolean isFree;

    @ColumnDefault("false")
    private boolean isBillGenerated;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "booked_table")
    @JsonIgnore
    private Tables table;

    //@OneToOne(mappedBy = "tableBookingBill")
    @OneToOne
    private OrderBill orderBill;

    //@OneToOne(mappedBy = "tableBookingOrder")
    @OneToOne
    private Order orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableBookingMenu")
    private List<OrderMenu> orderedMenu;
}