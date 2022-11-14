package com.usman.L3Test.service;

import com.usman.L3Test.entities.Rewards;
import com.usman.L3Test.repository.RewardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    RewardsRepository rewardsRepository;

    @Override
    public List<Rewards> getRewards() {
        return rewardsRepository.findAllByDate();
    }
}