package com.example.demo.model.moneyDTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MoneyDTO {
	private int moneyID;
	private LocalDateTime moneydate;
	private String moneytype;
	private String moneyname;
	private String change_money;
	private String userid;
}
