package com.example.demo.service.reward;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.reward.RewardMapper;
import com.example.demo.model.moneyDTO.MoneyDTO;

@Service
public class RewardServiceImpl implements RewardService{

	@Autowired
	RewardMapper rmapper;
	
	@Override
	public void putreward(MoneyDTO reward) {
		rmapper.putreward(reward);
	}

	@Override
	public List<MoneyDTO> getreward(String userid) {
		return rmapper.getreward(userid);
	}

}
