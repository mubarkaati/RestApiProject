package com.usman.restaurantBooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tableTypeId;

    @Column(nullable = false)
    private int tableCapacity;

    @Column(nullable = false)
    private int totalNumberOfTable;

    @Column(nullable = false)
    private int availableTable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "table")
    @JsonIgnore
    private List<TableBooking> tableBookings;
}