package com.example.demo.controller.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.service.goods.GoodsService;

@Controller
@RequestMapping("goods/*")
public class GoodsController {
	
	@Autowired
	private GoodsService service;
	
	@GetMapping("goods")
	public void goods(Model model) {
		List<GoodsDTO> goods = service.getgoods();
		model.addAttribute("goods", goods);
	}
	
	@GetMapping("prize")
	public void prize() {
		
	}
}
