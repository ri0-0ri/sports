package com.example.demo.model.payment;

import lombok.Data;

@Data
public class OrderListDTO {
	private int orderListid;
	private int ordernum;
	private int buynum;
	private String userid;
	private int goodsnum;
	private String size;
	private int quantity;
}
