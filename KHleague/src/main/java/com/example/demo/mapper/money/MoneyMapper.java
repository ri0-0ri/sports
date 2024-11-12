package com.example.demo.mapper.money;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.moneyDTO.MoneyDTO;

@Mapper
public interface MoneyMapper {

	void putmoney(MoneyDTO money);

	List<MoneyDTO> getmoney(String userid);

	List<MoneyDTO> getallmoney();

	void updatemoney(MoneyDTO money);

	List<MoneyDTO> getmoneyByordernum(String userid, int ordernum);
	
}
