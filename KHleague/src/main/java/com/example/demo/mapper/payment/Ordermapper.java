package com.example.demo.mapper.payment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.payment.OrderDTO;

@Mapper
public interface Ordermapper {

	void putorder(OrderDTO order);

	List<OrderDTO> getorderByuser(String userid);

	void putorderList(BuyListDTO buyListDTO);

	void deletebuyList(int buynum);

}
