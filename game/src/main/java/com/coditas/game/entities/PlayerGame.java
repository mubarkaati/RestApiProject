package com.coditas.game.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Data
public class GameDashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dashboardId;

    @Value("100")
    private int playerHealth;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player")
    @JsonIgnore
    private Player player;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "coordinate")
    @JsonIgnore
    private Coordinate coordinate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "game")
    @JsonIgnore
    private Game game;
}