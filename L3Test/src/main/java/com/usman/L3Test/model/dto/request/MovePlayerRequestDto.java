package com.usman.L3Test.model.dto.request;

import lombok.Data;

@Data
public class MovePlayerRequestDto {
    private int gameId;
    private int playerId;
    private int playerPositionX;
    private int playerPositionY;
}