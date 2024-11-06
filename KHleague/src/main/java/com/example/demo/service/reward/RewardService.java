package com.example.demo.service.reward;

import java.util.List;

import com.example.demo.model.moneyDTO.MoneyDTO;

public interface RewardService {

	void putreward(MoneyDTO reward);

	List<MoneyDTO> getreward(String userid);

}
