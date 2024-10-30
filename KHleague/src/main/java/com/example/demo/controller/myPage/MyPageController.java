package com.example.demo.controller.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/")
public class MyPageController {

	@GetMapping("mypage_profile")
	public String ShowMyPage_profile() {
		return "mypage/mypage_profile";
	}

	@GetMapping("mypage_money")
	public String Showmypage_money() {
		return "mypage/mypage_money";
	}

	@GetMapping("mypage_order")
	public String Showmypage_order() {
		return "mypage/mypage_order";
	}

	@GetMapping("mypage_wish")
	public String ShowMyPage_wish() {
		return "mypage/mypage_wish";
	}

	@GetMapping("mypage_buy")
	public String ShowMyPage_buy() {
		return "mypage/mypage_buy";
	}

}
