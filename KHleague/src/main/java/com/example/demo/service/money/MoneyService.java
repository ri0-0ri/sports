package com.example.demo.service.money;

import java.util.List;

import com.example.demo.model.moneyDTO.MoneyDTO;

public interface MoneyService {

	void putmoney(MoneyDTO money);

	List<MoneyDTO> getmoney(String userid);

	void change_money();

	List<MoneyDTO> getmoneyByordernum(String userid, int ordernum);

}
