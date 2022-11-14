package com.usman.L3Test.entities;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gameId;

    private String gameName;

    private int player1Id;

    private int player2Id;

    @ColumnDefault(value = "-1")
    private int player1PositionX;

    @ColumnDefault(value = "0")
    private int player1PositionY;

    @ColumnDefault(value = "2")
    private int player2PositionX;

    @ColumnDefault(value = "0")
    private int player2PositionY;

    @ColumnDefault(value = "100")
    private int player1Health;

    @ColumnDefault(value = "100")
    private int player2Health;

    @ColumnDefault(value = "0")
    private boolean isActive;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Rewards> rewards;
}