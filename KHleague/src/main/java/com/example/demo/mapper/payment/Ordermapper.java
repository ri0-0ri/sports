package com.example.demo.mapper.payment;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.payment.OrderDTO;

@Mapper
public interface Ordermapper {

	void putorder(OrderDTO order);

}
