package com.example.demo.goods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("goods/*")
public class GoodsController {
	
	@GetMapping("goods")
	public void goods() {
		
	}
	
	@GetMapping("prize")
	public void prize() {
		
	}
}
