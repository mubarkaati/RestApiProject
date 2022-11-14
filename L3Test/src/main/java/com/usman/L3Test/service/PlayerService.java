package com.usman.L3Test.service;

import com.usman.L3Test.model.dto.request.CreateGameRequestDto;
import com.usman.L3Test.model.dto.request.PlayerRequestDto;
import com.usman.L3Test.model.dto.response.CreateGameResponseDto;
import com.usman.L3Test.model.dto.response.PlayerResponseDto;

public interface PlayerService {
    PlayerResponseDto registerPlayer(PlayerRequestDto requestDto);

    CreateGameResponseDto createGame(CreateGameRequestDto requestDto);
}
