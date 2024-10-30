package com.example.demo.controller.myPage;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.goods.GoodsDTO;
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
	 public String ShowMyPage_wish(Model model, HttpSession session) {
	      String userid = (String)session.getAttribute("loginUser");
	      
	      List<GoodsDTO> goodsWishList = gservice.getWishgoods(userid);
	      model.addAttribute("goodsList", goodsWishList);	      
	      
		return "mypage/mypage_wish";
	}

	@PostMapping("mypage_wish")
	 public void mypage_wish(int goodsnum, String userid) {
		gservice.putWish(goodsnum, userid);
	}

	@GetMapping("mypage_buy")
	public String showMyPageBuy(Model model, HttpSession session) {
	    String userid = (String)session.getAttribute("loginUser");
		List<BuyListDTO> goodsBuyinfo = gservice.getBuygoods(userid);
	    model.addAttribute("goodsBuyinfo", goodsBuyinfo);
	    
//	    List<Integer> goodsNums = goodsBuyinfo.stream()
//	                                          .map(BuyListDTO::getGoodsnum)
//	                                          .collect(Collection.toList());

//	    List<GoodsDTO> goodsInfo = gservice.goodsInfo(goodsNums);
//	    model.addAttribute("goodsInfo", goodsInfo);
	    
	    System.out.println(goodsBuyinfo.size());
		return "mypage/mypage_buy";
	}
	
	@PostMapping("mypage_buy")
	public void mypage_buy(int goodsnum, String userid, String size, int quantity) {
		gservice.putBuy(goodsnum, userid, size, quantity);
	}
}
