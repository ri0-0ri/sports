package com.example.demo.model.payment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
    
    // 주문 상태를 업데이트하는 메서드
	/*
	 * public void updateState() { LocalDate now = LocalDate.now(); long daysBetween
	 * = ChronoUnit.DAYS.between(orderdatetime, now);
	 * 
	 * if(daysBetween==1) { state=""; } }
	 */
}
