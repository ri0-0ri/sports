package com.example.demo.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String Home() {
		return "index";
	}

	@GetMapping("/join/join.html")
	public String join() {
		return "join/join";
	}

	@GetMapping("/login_page/login_page.html")
	public String loginPage() {
		return "login_page/login_page"; // 해당 페이지의 뷰 이름을 반환
	}
}
