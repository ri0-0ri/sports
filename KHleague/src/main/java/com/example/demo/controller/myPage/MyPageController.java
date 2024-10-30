package com.example.demo.controller.myPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.service.goods.GoodsService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage/")
public class MyPageController {
	
	@Autowired
	GoodsService gservice;

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
		return "/mypage/mypage_order";
	}

	@GetMapping("mypage_wish")
	public String ShowMyPage_wish(Model model, HttpSession session) {
		String userid = (String)session.getAttribute("loginUser");
		
		List<GoodsDTO> goodsWishList = gservice.getWishgoods(userid);
		model.addAttribute("goodsList", goodsWishList);
		
		System.out.println(goodsWishList.size());
		
		return "mypage/mypage_wish";
	}
	
	@PostMapping("mypage_wish")
	public void mypage_wish(int goodsnum, String userid) {
		gservice.putWish(goodsnum, userid);
	}

	@GetMapping("mypage_buy")
	public String ShowMyPage_buy() {
		return "mypage/mypage_buy";
	}

}
