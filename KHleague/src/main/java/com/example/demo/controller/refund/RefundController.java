package com.example.demo.controller.refund;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.UserDTO.UserDTO;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.model.payment.OrderDTO;
import com.example.demo.model.payment.OrderListDTO;
import com.example.demo.service.goods.GoodsService;
import com.example.demo.service.payment.PaymentService;
import com.example.demo.service.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("refund/*")
public class RefundController {
	
	@Autowired
	PaymentService pservice;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	GoodsService gservice;

	@GetMapping("refund")
    public void refund(@RequestParam int ordernum, Model model) {
		OrderDTO order = pservice.getorderByordernum(ordernum);
		System.out.println("받아온order"+order);
		
		String userid = order.getUserid();
		UserDTO user = uservice.findUserById(userid);
		System.out.println("받아온user"+user);
		
		List<OrderListDTO> orderLists = pservice.getorderlistByordernum(ordernum, userid);
		System.out.println("받아온orderlists"+orderLists);
		
		for(OrderListDTO orderlist : orderLists) {
			int goodsnum = orderlist.getGoodsnum();
			List<GoodsDTO> goods = gservice.getgoodsBygoodsnum(goodsnum);
			System.out.println("받아온 goods"+goods);
			model.addAttribute("goods", goods);
		}
		
		model.addAttribute("order", order);
		model.addAttribute("user", user);
		model.addAttribute("orderLists", orderLists);
    }
	
}
