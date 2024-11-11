package com.example.demo.model.payment;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderDTO {
	private int ordernum;
	private LocalDate orderdatetime;
    private String deliveryPlace;
    private String deliveryMemo;
    private int totalPrice;
    private int sudannum;
    private String userid;
    private String ordername;
    private String buynum;
    private String state;
}
