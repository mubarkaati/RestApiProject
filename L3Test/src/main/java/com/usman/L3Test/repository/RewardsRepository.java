package com.usman.L3Test.repository;

import com.usman.L3Test.entities.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardsRepository extends JpaRepository<Rewards, Integer> {
    @Query("select rewards from Rewards rewards order by rewards.gameDate")
    List<Rewards> findAllByDate();
}