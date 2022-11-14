package com.usman.L3Test.service;

import com.usman.L3Test.entities.Game;
import com.usman.L3Test.entities.Player;
import com.usman.L3Test.entities.Rewards;
import com.usman.L3Test.exception.PositionOccupiedException;
import com.usman.L3Test.model.dto.request.MovePlayerRequestDto;
import com.usman.L3Test.model.dto.request.ShootPlayerRequestDto;
import com.usman.L3Test.repository.GameRepository;
import com.usman.L3Test.repository.PlayerRepository;
import com.usman.L3Test.repository.RewardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    RewardsRepository rewardsRepository;

    @Autowired
    PlayerRepository playerRepository;

    public boolean movePlayer(MovePlayerRequestDto requestDto) {
        Game game = gameRepository.findById(requestDto.getGameId()).orElse(null);

        if (game.isActive()) {
            int x1 = 0;
            int x2 = 0;
            int y1 = 0;
            int y2 = 0;

            int whichPlayer = 0;

            if (game.getPlayer1Id() == requestDto.getPlayerId()) {
                whichPlayer = 1;
                x1 = game.getPlayer1PositionX();
                x2 = requestDto.getPlayerPositionX();

                y1 = game.getPlayer1PositionY();
                y2 = requestDto.getPlayerPositionY();

                if (requestDto.getPlayerPositionX() == game.getPlayer2PositionX() && requestDto.getPlayerPositionY() == game.getPlayer2PositionY()) {
                    throw new PositionOccupiedException();
                }

            } else if (game.getPlayer2Id() == requestDto.getPlayerId()) {
                x1 = game.getPlayer2PositionX();
                x2 = requestDto.getPlayerPositionX();

                y1 = game.getPlayer2PositionY();
                y2 = requestDto.getPlayerPositionY();

                if (requestDto.getPlayerPositionX() == game.getPlayer1PositionX() && requestDto.getPlayerPositionY() == game.getPlayer1PositionY()) {
                    throw new PositionOccupiedException();
                }
            }
            int partialDistance = (((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
            if (partialDistance < 0) {
                partialDistance = partialDistance * (-1);
            }
            int distance = (int) Math.sqrt(partialDistance);
            if (distance == 1) {
                if (whichPlayer == 1) {
                    game.setPlayer1PositionX(requestDto.getPlayerPositionX());
                    game.setPlayer1PositionY(requestDto.getPlayerPositionY());
                } else {
                    game.setPlayer2PositionX(requestDto.getPlayerPositionX());
                    game.setPlayer2PositionY(requestDto.getPlayerPositionY());
                }
                gameRepository.save(game);
                return true;
            } else {
                return false;
            }
        } else {
            //validation part need to be done later
            return false;
        }
    }

    @Override
    public boolean shootPlayer(ShootPlayerRequestDto requestDto) {
        Game game = gameRepository.findById(requestDto.getGameId()).orElse(null);

        if (game.isActive()) {
            int x = requestDto.getShootPositionX();
            int y = requestDto.getShootPositionY();

            if (requestDto.getPlayerId() == game.getPlayer1Id()) {
                if ((x == game.getPlayer2PositionX()) && (y == game.getPlayer2PositionY())) {
                    game.setPlayer2Health(game.getPlayer2Health() - 20);
                }
            } else if (requestDto.getPlayerId() == game.getPlayer2Id()) {
                if ((x == game.getPlayer1PositionX()) && (y == game.getPlayer1PositionY())) {
                    game.setPlayer2Health(game.getPlayer1Health() - 20);
                }
            }

            if ((game.getPlayer1Health() == 0) || (game.getPlayer2Health() == 0)) {
                //set game status false cause game is over
                game.setActive(false);
                if (game.getPlayer2Health() == 0) {
                    Rewards rewards = new Rewards();
                    rewards.setPlayer(playerRepository.findById(game.getPlayer1Id()).orElse(null));
                    rewards.setGame(game);
                    rewards.setRewardsPoint(100);
                    rewards.setGameDate(LocalDate.now());
                    rewardsRepository.save(rewards);
                } else {
                    Rewards rewards = new Rewards();
                    rewards.setPlayer(playerRepository.findById(game.getPlayer2Id()).orElse(null));
                    rewards.setGame(game);
                    rewards.setRewardsPoint(100);
                    rewards.setGameDate(LocalDate.now());
                    rewardsRepository.save(rewards);
                }

                Player player1 = playerRepository.findById(game.getPlayer1Id()).orElse(null);
                Player player2 = playerRepository.findById(game.getPlayer2Id()).orElse(null);

                player1.setInGame(false);
                player2.setInGame(false);

                //save status free of both player
                playerRepository.save(player1);
                playerRepository.save(player2);
            }

            gameRepository.save(game);
            return true;
        } else {
            //validation part need to be done later
            return false;
        }
    }
}