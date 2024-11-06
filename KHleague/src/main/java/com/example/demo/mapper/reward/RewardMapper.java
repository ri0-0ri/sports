package com.example.demo.mapper.reward;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.moneyDTO.MoneyDTO;

@Mapper
public interface RewardMapper {

	void putreward(MoneyDTO reward);

	List<MoneyDTO> getreward(String userid);
	
}
