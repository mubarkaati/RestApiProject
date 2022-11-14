package com.usman.L3Test.model.dto.request;

import lombok.Data;

@Data
public class ShootPlayerRequestDto {
    private int gameId;
    private int playerId;
    private int shootPositionX;
    private int shootPositionY;
}