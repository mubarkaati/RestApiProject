package com.usman.L3Test.repository;

import com.usman.L3Test.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Query("select (count(player) = 2) from Player player " +
            "where player.playerId in ?1 and player.inGame = false")
    boolean isPlayersFree(int... playersId);

    @Transactional
    @Modifying
    @Query("update Player p set p.inGame = 1 where p.playerId in ?1")
    int setPlayerInGame(int... playersId);

    @Transactional
    @Modifying
    @Query("update Player player set player.inGame = false where player.playerId in ?1")
    void updatePlayerStatus(int... playersId);
}