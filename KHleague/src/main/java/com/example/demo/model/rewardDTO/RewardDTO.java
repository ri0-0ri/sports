package com.example.demo.model.rewardDTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RewardDTO {
	private int rewardID;
	private LocalDate rewarddate;
	private String rewardname;
	private int change_reward;
	private String userid;
}
