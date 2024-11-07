package com.example.demo.service.payment;

import java.util.List;

import com.example.demo.model.payment.OrderDTO;

public interface PaymentService {

	void putorder(OrderDTO order);

	List<OrderDTO> getorderByuser(String userid);

}
