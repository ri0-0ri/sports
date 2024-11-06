package com.example.demo.service.money;

import java.util.List;

import com.example.demo.model.moneyDTO.MoneyDTO;

public interface MoneyService {

	void putmoney(MoneyDTO money);

	List<MoneyDTO> getmoney(String userid);

}
