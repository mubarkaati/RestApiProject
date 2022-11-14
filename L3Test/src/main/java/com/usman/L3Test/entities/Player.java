package com.usman.L3Test.entities;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int playerId;

    private String playerName;

    @ColumnDefault(value = "0")
    private boolean inGame;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Rewards> rewards;
}