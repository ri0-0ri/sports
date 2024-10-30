package com.example.demo.controller.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.goods.GoodsService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage/")
public class MyPageController {

	@Autowired
	GoodsService gservice;

	@GetMapping("mypage_profile")
	public String showMyPageProfile(HttpSession session, Model model) {
		String userid = (String) session.getAttribute("loginUser");
		String username = (String) session.getAttribute("username");
		String useraddr = (String) session.getAttribute("useraddr"); 
		String userphone = (String) session.getAttribute("userphone"); 

		
		if (userid != null) {
			model.addAttribute("userid", userid);
			model.addAttribute("username", username);
			model.addAttribute("useraddr", useraddr);
			model.addAttribute("userphone", userphone); 
		}

		return "mypage/mypage_profile";
	}

	@GetMapping("mypage_money")
	public String showMypageMoney() {
		return "mypage/mypage_money";
	}

	@GetMapping("mypage_order")
	public String showMypageOrder() {
		return "/mypage/mypage_order";
	}

	@GetMapping("mypage_wish")
	public String showMyPageWish(Model model, String userid) {
		// List<GoodsDTO> goodsWishList = gservice.getWishgoods(userid);
		// model.addAttribute("goodsList", goodsWishList);
		return "mypage/mypage_wish";
	}

	@PostMapping("mypage_wish")
	public void mypageWish(int goodsnum, String userid) {
		System.out.println("Goods Number: " + goodsnum + ", User ID: " + userid);
		gservice.putWish(goodsnum, userid);
	}

	@GetMapping("mypage_buy")
	public String showMyPageBuy() {
		return "mypage/mypage_buy";
	}
}
