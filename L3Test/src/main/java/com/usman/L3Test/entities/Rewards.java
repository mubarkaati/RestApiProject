package com.usman.L3Test.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Rewards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rewardId;

    private int rewardsPoint;

    private LocalDate gameDate = LocalDate.now();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Game game;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Player player;
}