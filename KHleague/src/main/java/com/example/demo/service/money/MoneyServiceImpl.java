package com.example.demo.service.money;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.money.MoneyMapper;
import com.example.demo.model.moneyDTO.MoneyDTO;

@Service
public class MoneyServiceImpl implements MoneyService{

	@Autowired
	MoneyMapper mmapper;
	
	@Override
	public void putmoney(MoneyDTO money) {
		mmapper.putmoney(money);
	}

	@Override
	public List<MoneyDTO> getmoney(String userid) {
		return mmapper.getmoney(userid);
	}

}
