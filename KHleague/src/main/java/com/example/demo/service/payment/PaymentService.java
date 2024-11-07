package com.example.demo.service.payment;

import java.util.List;

import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.payment.OrderDTO;

public interface PaymentService {

	void putorder(OrderDTO order);

	List<OrderDTO> getorderByuser(String userid);

	void putorderList(BuyListDTO buyListDTO);

	void deletebuyList(int buynum);

}
