package com.usman.L3Test.model.dto.request;

import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
public class CreateGameRequestDto {
    private int gameId = 0;
    private int player1Id;
    private int player2Id;
    private String gameName;
    private int player1PositionX;
    private int player1PositionY;
    private int player2PositionX;
    private int player2PositionY;

    private int player1Health = 100;

    private int player2Health = 100;

    private boolean isActive = true;

    public CreateGameRequestDto() {
        int xPoints = ThreadLocalRandom.current().nextInt(-10, 9);
        player1PositionX = xPoints;
        player1PositionY = xPoints + 1;
        xPoints = ThreadLocalRandom.current().nextInt(-10, 9);
        player2PositionX = xPoints;
        player2PositionY = xPoints + 1;
    }
}