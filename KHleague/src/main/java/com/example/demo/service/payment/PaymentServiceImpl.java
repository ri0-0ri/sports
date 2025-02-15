package com.example.demo.service.payment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

	@Override
	public void change_state() {
		List<OrderDTO> orderlists = omapper.getorders();
		LocalDate yesterday = LocalDate.now();
		for(OrderDTO order : orderlists) {
			LocalDate orderdate = order.getOrderdatetime();		
			long daysBetween = ChronoUnit.DAYS.between(orderdate, yesterday);
			
			System.out.println("Current date: " + yesterday);
			System.out.println("Order date: " + order.getOrderdatetime());
			System.out.println("Days between: " + daysBetween);
			
	        if (daysBetween >= 1 && daysBetween < 3) {
	            order.setState("상품준비중");
	        } else if (daysBetween >= 3 && daysBetween < 5) {
	            order.setState("배송준비중");
	        } else if (daysBetween >= 5 && daysBetween < 7) {
	            order.setState("배송중");
	        } else if (daysBetween >= 7) {
	            order.setState("배송완료");
	        }
			
			System.out.println("바뀐상황"+order.getState());
			
			omapper.updatestate(order);
		}
	}

	@Override
	public OrderDTO getorderByordernum(int ordernum) {
		return omapper.getorderByordernum(ordernum);
	}

	@Override
	public List<OrderListDTO> getorderlistByordernum(int ordernum, String userid) {
		return omapper.getorderlistByordernum(ordernum, userid);
	}

	@Override
	public void updateorder(OrderDTO order) {
		omapper.updatestate(order);
	}

	@Override
	public void deleteorderList(int ordernum) {
		omapper.deleteorderList(ordernum);
	}

	@Override
	public void putdelivery(OrderDTO order) {
		omapper.putdelivery(order);
	}

}
