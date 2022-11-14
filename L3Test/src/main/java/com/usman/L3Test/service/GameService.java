package com.usman.L3Test.service;

import com.usman.L3Test.model.dto.request.MovePlayerRequestDto;
import com.usman.L3Test.model.dto.request.ShootPlayerRequestDto;

public interface GameService {
    boolean movePlayer(MovePlayerRequestDto requestDto);

    boolean shootPlayer(ShootPlayerRequestDto requestDto);
}