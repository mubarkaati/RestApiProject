package com.usman.L3Test.controller;

import com.usman.L3Test.entities.Rewards;
import com.usman.L3Test.model.dto.request.MovePlayerRequestDto;
import com.usman.L3Test.model.dto.request.ShootPlayerRequestDto;
import com.usman.L3Test.service.GameService;
import com.usman.L3Test.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    GameService gameService;

    @Autowired
    RewardsService rewardsService;

    @PostMapping("/move")
    public ResponseEntity movePlayer(@RequestBody MovePlayerRequestDto requestDto) {
        boolean moveStatus = gameService.movePlayer(requestDto);
        if (moveStatus == true)
            return new ResponseEntity("player moved successfully!!", HttpStatus.OK);
        else
            return new ResponseEntity("invalid Coordinates try again!!", HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/shoot")
    public ResponseEntity shootPlayer(@RequestBody ShootPlayerRequestDto requestDto) {
        boolean shootStatus = gameService.shootPlayer(requestDto);
        if (shootStatus == true)
            return new ResponseEntity("shoot done successfully", HttpStatus.OK);
        else
            return new ResponseEntity("invalid game id or Game is already completed", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/getRewards")
    public ResponseEntity getRewards() {
        List<Rewards> rewardsList = rewardsService.getRewards();
        return new ResponseEntity(rewardsList, HttpStatus.OK);
    }
}