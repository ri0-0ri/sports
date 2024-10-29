package com.example.demo.controller.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/")
public class MyPageController {

	@GetMapping("mypage_profile")
	public String ShowMyPage() {
		return "mypage/mypage_profile";
	}
}
