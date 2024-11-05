package com.example.demo.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.payment.Ordermapper;
import com.example.demo.model.payment.OrderDTO;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	Ordermapper omapper;
	
	@Override
	public void putorder(OrderDTO order) {
		omapper.putorder(order);
	}

}
