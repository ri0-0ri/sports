package com.example.demo.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login_page/")
public class LoginController {

	
	@GetMapping("login_page")
	public String ShowloginPage() {
		return "login_page/login_page";
	}
	
}
