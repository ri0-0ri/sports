package com.example.demo.controller.payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.UserDTO.UserDTO;
import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.service.goods.GoodsService;
import com.example.demo.service.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("payment/*")
public class PaymentController {
	
	@Autowired
	GoodsService gservice;
	
	@Autowired
	UserService uservice;
	
	@GetMapping("payment")
	public String payment(@RequestParam List<Integer> buynum, Model model, HttpSession session) {
		System.out.println("Received buynum: " + buynum);
		List<BuyListDTO> goodsBuyinfo = new ArrayList<>();
		List<GoodsDTO> goodsinfo = new ArrayList<>();
		for(Integer singleBuynum : buynum) {
			goodsBuyinfo.add(gservice.getBuygoodsBybuynum(singleBuynum));
			goodsinfo.add(gservice.getgoodsBycart(gservice.getBuygoodsBybuynum(singleBuynum).getGoodsnum()));
		}
		model.addAttribute("goodsBuyinfo", goodsBuyinfo);	
		model.addAttribute("goodsinfo", goodsinfo);
		
		String userid = (String)session.getAttribute("loginUser");
		UserDTO user = uservice.findUserById(userid);
		model.addAttribute("user", user);
		System.out.println(user);
		
        return "payment/payment";
    }
	
	@PostMapping("okpayment")
	public void okpayment() {
		
	}
}
