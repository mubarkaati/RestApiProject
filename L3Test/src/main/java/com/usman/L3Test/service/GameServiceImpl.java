package com.usman.L3Test.service;

import com.usman.L3Test.entities.Game;
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
        Game game = gameRepository.findGameInfo(requestDto.getGameId());

        int newCoordinateX = requestDto.getPlayerPositionX();
        int newCoordinateY = requestDto.getPlayerPositionY();
        int player1X = game.getPlayer1PositionX();
        int player1Y = game.getPlayer1PositionY();
        int player2X = game.getPlayer2PositionX();
        int player2Y = game.getPlayer2PositionY();

        if (game.isActive()) {
            boolean player1 = game.getPlayer1Id() == requestDto.getPlayerId();
            int distance = player1 ? getUpdatedGame(player1X, player1Y, newCoordinateX, newCoordinateY) : getUpdatedGame(player2X, player2Y, newCoordinateX, newCoordinateY);
            if (distance == 1) {
                if (player1 ? getCollision(player2X, player2Y, newCoordinateX, newCoordinateY) : getCollision(player1X, player1Y, newCoordinateX, newCoordinateY))
                    throw new PositionOccupiedException();
                if (player1)
                    gameRepository.updateCoordinate1(game.getGameId(), newCoordinateX, newCoordinateY);
                else
                    gameRepository.updateCoordinate2(game.getGameId(), newCoordinateX, newCoordinateY);
                return true;
            } else {
                return false;
            }
        } else {
            //validation part need to be done later
            return false;
        }
    }

    private boolean getCollision(int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2)
            return true;
        else
            return false;
    }

    private int getUpdatedGame(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.abs(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))));
    }

    @Override
    public boolean shootPlayer(ShootPlayerRequestDto requestDto) {
        Game game = gameRepository.findById(requestDto.getGameId()).orElse(null);

        if (game.isActive()) {
            boolean isFirstPlayer = requestDto.getPlayerId() == game.getPlayer1Id();

            boolean hitStatus = isFirstPlayer ? game.getPlayer2PositionX() == requestDto.getShootPositionX()
                    && game.getPlayer2PositionY() == requestDto.getShootPositionY()
                    : game.getPlayer1PositionX() == requestDto.getShootPositionX()
                    && game.getPlayer1PositionY() == requestDto.getShootPositionY();

            if (hitStatus) {
                if (isFirstPlayer) {
                    game.setPlayer2Health(game.getPlayer2Health() - 20);
                } else {
                    game.setPlayer1Health(game.getPlayer1Health() - 20);
                }
                if (game.getPlayer1Health() == 0 || game.getPlayer2Health() == 0) {
                    game.setActive(false);
                    playerRepository.updatePlayerStatus(game.getPlayer1Id(), game.getPlayer2Id());
                    Rewards rewards = new Rewards();
                    rewards.setRewardsPoint(100);
                    rewards.setPlayer(playerRepository.findById(requestDto.getPlayerId()).orElse(null));
                    rewards.setGame(game);
                    rewardsRepository.save(rewards);
                }
                gameRepository.save(game);
            }
            return true;
        } else {
            //validation part need to be done later
            return false;
        }
    }
}