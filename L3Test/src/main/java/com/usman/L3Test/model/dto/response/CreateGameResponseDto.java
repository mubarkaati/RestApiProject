package com.usman.L3Test.model.dto.response;

import lombok.Data;

@Data
public class CreateGameResponseDto {
    private int gameId;
    private String gameName;
    private String player1Name;
    private String player2Name;
}