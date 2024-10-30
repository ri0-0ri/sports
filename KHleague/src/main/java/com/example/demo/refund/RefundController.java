package com.example.demo.refund;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("refund/*")
public class RefundController {

	@GetMapping("refund")
	public void refund() {
		
	}
}
