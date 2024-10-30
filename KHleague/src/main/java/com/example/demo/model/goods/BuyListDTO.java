package com.example.demo.model.goods;

import lombok.Data;

@Data
public class BuyListDTO {
	private int buynum;
	private String userid;
	private int goodsnum;
	private String size;
	private int quantity;
}
