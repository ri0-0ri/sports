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
		System.out.println("모든오더리스트!!"+orderlists);
		LocalDate now = LocalDate.now();
		for(OrderDTO order : orderlists) {
			System.out.println(order.getOrderdatetime());
			LocalDate orderdate = order.getOrderdatetime();
			
			long daysBetween = ChronoUnit.DAYS.between(orderdate, now);
			
			if(daysBetween==1) {
				order.setState("상품준비중");
			}
			else if(daysBetween==3) {
				order.setState("배송준비중");
			}
			else if(daysBetween==5) {
				order.setState("배송중");
			}
			else if(daysBetween==7) {
				order.setState("배송완료");
			}
			
			omapper.updatestate(order);
		}
	}

}
