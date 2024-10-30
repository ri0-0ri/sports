package com.example.demo.controller.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.service.goods.GoodsService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("goods/*")
public class GoodsController {
	
	@Autowired
	private GoodsService service;
	
	@GetMapping("goods")
	public void goods(Model model, HttpSession session) {
		String loginUser = (String)session.getAttribute("loginUser");
		List<GoodsDTO> goodsList = service.getgoods();
		model.addAttribute("goodsList", goodsList);
	}
	
	@GetMapping("prize")
	public void prize() {
		
	}
}
