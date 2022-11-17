package com.usman.L3Test.service;

import com.usman.L3Test.entities.Game;
import com.usman.L3Test.entities.Player;
import com.usman.L3Test.model.dto.request.CreateGameRequestDto;
import com.usman.L3Test.model.dto.request.PlayerRequestDto;
import com.usman.L3Test.model.dto.response.CreateGameResponseDto;
import com.usman.L3Test.model.dto.response.PlayerResponseDto;
import com.usman.L3Test.repository.GameRepository;
import com.usman.L3Test.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PlayerResponseDto registerPlayer(PlayerRequestDto requestDto) {
        Player player = this.modelMapper.map(requestDto, Player.class);

        Player savedPlayer = playerRepository.save(player);

        return this.modelMapper.map(savedPlayer, PlayerResponseDto.class);
    }

    @Override
    public CreateGameResponseDto createGame(CreateGameRequestDto requestDto) {
        boolean playerFree = playerRepository.isPlayersFree(requestDto.getPlayer1Id(), requestDto.getPlayer2Id());
        if (playerFree) {
            playerRepository.setPlayerInGame(requestDto.getPlayer1Id(), requestDto.getPlayer2Id());
            Game game = this.modelMapper.map(requestDto, Game.class);
            Game savedGame = gameRepository.save(game);
            CreateGameResponseDto responseDto = this.modelMapper.map(savedGame, CreateGameResponseDto.class);
            return responseDto;
        } else {
            return null;
        }
    }
}

//
//package com.usman.L3Test.service;
//
//        import com.usman.L3Test.entities.Game;
//        import com.usman.L3Test.entities.Player;
//        import com.usman.L3Test.model.dto.request.CreateGameRequestDto;
//        import com.usman.L3Test.model.dto.request.PlayerRequestDto;
//        import com.usman.L3Test.model.dto.response.CreateGameResponseDto;
//        import com.usman.L3Test.model.dto.response.PlayerResponseDto;
//        import com.usman.L3Test.repository.GameRepository;
//        import com.usman.L3Test.repository.PlayerRepository;
//        import org.modelmapper.ModelMapper;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.stereotype.Service;
//
//@Service
//public class PlayerServiceImpl implements PlayerService {
//
//    @Autowired
//    PlayerRepository playerRepository;
//
//    @Autowired
//    GameRepository gameRepository;
//
//    @Autowired
//    ModelMapper modelMapper;
//
//    @Override
//    public PlayerResponseDto registerPlayer(PlayerRequestDto requestDto) {
//        Player player = this.modelMapper.map(requestDto, Player.class);
//
//        Player savedPlayer = playerRepository.save(player);
//
//        return this.modelMapper.map(savedPlayer, PlayerResponseDto.class);
//    }
//
//    @Override
//    public CreateGameResponseDto createGame(CreateGameRequestDto requestDto) {
//        boolean playerFree = playerRepository.isPlayersFree();
//        Player player1 = playerRepository.findById(requestDto.getPlayer1Id()).orElse(null);
//        Player player2 = playerRepository.findById(requestDto.getPlayer2Id()).orElse(null);
//
//        if (!(player1.isInGame() && (player2.isInGame()))) {
//            Game game = new Game();
//            game.setGameName(requestDto.getGameName());
//            game.setPlayer1Id(player1.getPlayerId());
//            game.setPlayer2Id(player2.getPlayerId());
//            game.setActive(true);
//
//            Game savedGame = gameRepository.save(game);
//
//            player1.setInGame(true);
//            player2.setInGame(true);
//
//            playerRepository.save(player1);
//            playerRepository.save(player2);
//
//            CreateGameResponseDto responseDto = this.modelMapper.map(savedGame, CreateGameResponseDto.class);
//            responseDto.setPlayer1Name(player1.getPlayerName());
//            responseDto.setPlayer2Name(player2.getPlayerName());
//
//            return responseDto;
//        } else {
//            return null;
//        }
//    }
//}