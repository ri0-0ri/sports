package com.example.demo.service.payment;

import java.util.List;

import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.payment.OrderDTO;
import com.example.demo.model.payment.OrderListDTO;

public interface PaymentService {

	void putorder(OrderDTO order);

	List<OrderDTO> getorderByuser(String userid);

	void putorderList(OrderListDTO buyListDTO);

	void deletebuyList(int buynum);

	int getorderlastnum();

	List<OrderListDTO> getorderlist(String userid);

	void change_state();

}
