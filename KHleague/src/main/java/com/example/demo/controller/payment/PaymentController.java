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
		long totalAmount = 5000;
		List<BuyListDTO> goodsBuyinfo = new ArrayList<>();
		Map<Integer, GoodsDTO> goodsMap = new HashMap<>();
		for (Integer singleBuynum : buynum) {
		    BuyListDTO buyListDTO = gservice.getBuygoodsBybuynum(singleBuynum);
		    GoodsDTO goodsDTO = gservice.getgoodsBycart(buyListDTO.getGoodsnum());
		    goodsBuyinfo.add(buyListDTO);
		    goodsMap.put(buyListDTO.getGoodsnum(), goodsDTO);
		    totalAmount += gservice.getgoodsBycart(buyListDTO.getGoodsnum()).getGoodsprice() * gservice.getBuygoodsBybuynum(singleBuynum).getQuantity();
		    System.out.println(gservice.getgoodsBycart(buyListDTO.getGoodsnum()).getGoodsprice());
		    System.out.println(gservice.getBuygoodsBybuynum(singleBuynum).getQuantity());
		}	    
	    model.addAttribute("goodsBuyinfo", goodsBuyinfo);
	    model.addAttribute("goodsMap", goodsMap);
	    model.addAttribute("total", totalAmount);

	    String userid = (String) session.getAttribute("loginUser");
	    UserDTO user = uservice.findUserById(userid);
	    model.addAttribute("user", user);
		
		
        return "payment/payment";
    }
	
}
