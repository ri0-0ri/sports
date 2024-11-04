package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	
	@GetMapping("admin_time")
	public String showAdmin_time() {
		return "admin/admin_time";
	}
	@GetMapping("admin_user")
	public String showAdmin_user() {
		return "admin/admin_user";
	}
	
}
