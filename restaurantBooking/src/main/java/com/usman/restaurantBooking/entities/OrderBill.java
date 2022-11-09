package com.usman.restaurantBooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
public class OrderBill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int billId;

    @Column(nullable = false)
    private float totalAmount;

    @ColumnDefault("false")
    private boolean isAmountPaid;

    @OneToOne
//    @JoinColumn(name = "table_booking_bill")
    @JsonIgnore
    private TableBooking tableBookingBill;
}