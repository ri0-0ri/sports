package com.example.demo.service.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.payment.Ordermapper;
import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.payment.OrderDTO;
import com.example.demo.model.payment.OrderListDTO;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	Ordermapper omapper;
	
	@Override
	public void putorder(OrderDTO order) {
		omapper.putorder(order);
	}

	@Override
	public List<OrderDTO> getorderByuser(String userid) {
		return omapper.getorderByuser(userid);
	}

	@Override
	public void putorderList(OrderListDTO buyListDTO) {
		omapper.putorderList(buyListDTO);
	}

	@Override
	public void deletebuyList(int buynum) {
		omapper.deletebuyList(buynum);
	}

	@Override
	public int getorderlastnum() {
		return omapper.getorderlastnum();
	}

	@Override
	public List<OrderListDTO> getorderlist(String userid) {
		return omapper.getorderlist(userid);
	}

}
