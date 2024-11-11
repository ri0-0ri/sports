package com.example.demo.mapper.payment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.payment.OrderDTO;
import com.example.demo.model.payment.OrderListDTO;

@Mapper
public interface Ordermapper {

	void putorder(OrderDTO order);

	List<OrderDTO> getorderByuser(String userid);

	void putorderList(OrderListDTO buyListDTO);

	void deletebuyList(int buynum);

	int getorderlastnum();

	List<OrderListDTO> getorderlist(String userid);

	List<OrderDTO> getorders();

	void updatestate(OrderDTO order);

	OrderDTO getorderByordernum(int ordernum);

	List<OrderListDTO> getorderlistByordernum(int ordernum, String userid);

}
