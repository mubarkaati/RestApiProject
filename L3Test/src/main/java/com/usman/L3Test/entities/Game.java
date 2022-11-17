package com.usman.L3Test.entities;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

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

    private int player1PositionX;

    private int player1PositionY;

    private int player2PositionX;

    private int player2PositionY;

    private int player1Health;

    private int player2Health;

    @Value("1")
    private boolean isActive;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Rewards> rewards;
}