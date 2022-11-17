package com.usman.L3Test.model.dto.response;

import lombok.Data;

@Data
public class CreateGameResponseDto {
    private int gameId;
    private String gameName;
    private int player1Id;
    private int player2Id;
}