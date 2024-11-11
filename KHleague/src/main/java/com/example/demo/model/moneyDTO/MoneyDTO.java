package com.example.demo.model.moneyDTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MoneyDTO {
	private int moneyId;
	private LocalDateTime moneydate;
	private String moneytype;
	private String moneyname;
	private String changeMoney;
	private String userid;
}
