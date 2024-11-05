package com.example.demo.controller.payment;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.UserDTO.UserDTO;
import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.model.payment.OrderDTO;
import com.example.demo.service.goods.GoodsService;
import com.example.demo.service.payment.PaymentService;
import com.example.demo.service.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("payment/*")
public class PaymentController {
	
	@Autowired
	GoodsService gservice;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	PaymentService pservice;
	
	@GetMapping("payment")
	public String payment(@RequestParam List<Integer> buynum, Model model, HttpSession session) {	
		long totalAmount = 5000;
		List<BuyListDTO> goodsBuyinfo = new ArrayList<>();
		Map<Integer, GoodsDTO> goodsMap = new HashMap<>();
		for (Integer singleBuynum : buynum) {
		    BuyListDTO buyListDTO = gservice.getBuygoodsBybuynum(singleBuynum);
		    GoodsDTO goodsDTO = gservice.getgoodsBycart(buyListDTO.getGoodsnum());
		    goodsBuyinfo.add(buyListDTO);
		    goodsMap.put(buyListDTO.getGoodsnum(), goodsDTO);
		    totalAmount += gservice.getgoodsBycart(buyListDTO.getGoodsnum()).getGoodsprice() * gservice.getBuygoodsBybuynum(singleBuynum).getQuantity();
//		    System.out.println(gservice.getgoodsBycart(buyListDTO.getGoodsnum()).getGoodsprice());
//		    System.out.println(gservice.getBuygoodsBybuynum(singleBuynum).getQuantity());
		}	    
	    model.addAttribute("goodsBuyinfo", goodsBuyinfo);
	    model.addAttribute("goodsMap", goodsMap);
	    model.addAttribute("total", totalAmount);

	    String userid = (String) session.getAttribute("loginUser");
	    UserDTO user = uservice.findUserById(userid);
	    model.addAttribute("user", user);
		
		
        return "payment/payment";
    }
	
	@PostMapping("okpayment")
	public String okpayment(OrderDTO order,  @RequestParam String userpoint) {	
		String userid = order.getUserid();
		int point = 0;
		// sudannum 1은 포인트결제
		if(order.getSudannum()==1) {			
			// 넘어온 userpoint가 0보다 크면 충전했다는 뜻
			// 기존 유저가 가지고있는 포인트가 결제할 금액보다 작아서 그만큼 충전해야한다는의미
			// 기존 유저포인트 불러와서 충전할 포인트 더하고 총 금액 마이너스해야함
			if(Integer.parseInt(userpoint)>0) {			
				point = (uservice.findUserById(userid).getUserpoint()+Integer.parseInt(userpoint))-order.getTotalPrice();
			}
			// 넘어온 userpoint가 0보다 작거나 같다면 충전하지 않았다는뜻
			// 기존 유저포인트가져와서 결제할 금액 빼면 나머지 포인트
			else {
				point = uservice.findUserById(userid).getUserpoint()-order.getTotalPrice();
			}
			System.out.println("남은포인트"+point);		
			pservice.putorder(order);
			uservice.putpoint(point, userid);
		}
		// sudannum 2는 간편결제
		else {
			pservice.putorder(order);
		}		
		
		return "redirect:/mypage/mypage_order";		
	}
	
}
