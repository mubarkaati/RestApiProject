package com.usman.L3Test.repository;

import com.usman.L3Test.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query("select game from Game game where game.gameId = ?1")
    Game findGameInfo(int gameId);

    @Transactional
    @Modifying
    @Query("update Game game set game.player1PositionX = ?2, game.player1PositionY = ?3 where game.gameId = ?1")
    void updateCoordinate1(int gameId, int player1PositionX, int player1PositionY);

    @Transactional
    @Modifying
    @Query("update Game game set game.player2PositionX = ?2, game.player2PositionY = ?3 where game.gameId = ?1")
    void updateCoordinate2(int gameId, int player2PositionX, int player2PositionY);
}