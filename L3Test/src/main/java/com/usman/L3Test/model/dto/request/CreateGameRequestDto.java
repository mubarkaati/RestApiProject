package com.usman.L3Test.model.dto.request;

import lombok.Data;

@Data
public class CreateGameRequestDto {
    private int player1Id;
    private int player2Id;
    private String gameName;
}