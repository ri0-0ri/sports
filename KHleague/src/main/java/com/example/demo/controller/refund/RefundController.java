package com.example.demo.controller.refund;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.payment.OrderDTO;
import com.example.demo.service.payment.PaymentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("refund/*")
public class RefundController {

	@GetMapping("refund")
	public void refund() {
		
	}
	
}
