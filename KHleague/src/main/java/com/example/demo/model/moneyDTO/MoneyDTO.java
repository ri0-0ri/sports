package com.example.demo.model.moneyDTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MoneyDTO {
	private int rewardID;
	private LocalDate rewarddate;
	private String rewardname;
	private String change_reward;
	private String userid;
}
