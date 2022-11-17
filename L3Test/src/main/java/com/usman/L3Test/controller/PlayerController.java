package com.usman.L3Test.controller;

import com.usman.L3Test.model.dto.request.CreateGameRequestDto;
import com.usman.L3Test.model.dto.request.PlayerRequestDto;
import com.usman.L3Test.model.dto.response.CreateGameResponseDto;
import com.usman.L3Test.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/register")
    public ResponseEntity registerPlayer(@RequestBody PlayerRequestDto requestDto) {
        return new ResponseEntity(playerService.registerPlayer(requestDto), HttpStatus.OK);
    }

    @PostMapping("/createGame")
    public ResponseEntity createGame(@RequestBody CreateGameRequestDto requestDto) {
        CreateGameResponseDto responseDto = playerService.createGame(requestDto);
        if (responseDto != null)
            return new ResponseEntity(responseDto, HttpStatus.OK);
        else
            return new ResponseEntity("Players not free try again", HttpStatus.NOT_ACCEPTABLE);
    }
}