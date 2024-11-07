package com.example.demo.controller.myPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.UserDTO.UserDTO;
import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.model.moneyDTO.MoneyDTO;
import com.example.demo.service.goods.GoodsService;
import com.example.demo.service.money.MoneyService;
import com.example.demo.service.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage/")
public class MyPageController {

	@Autowired
	GoodsService gservice;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	MoneyService mservice;

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
	public String showMypageMoney(Model model, HttpSession session) {
		String userid = (String)session.getAttribute("loginUser");
		UserDTO user = uservice.findUserById(userid);
		model.addAttribute("user", user);
		
		List<MoneyDTO> money = mservice.getmoney(userid);
		model.addAttribute("money", money);
		System.out.println(money);
		
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
//		장바구니 테이블 내역을 가지고오기
	    String userid = (String)session.getAttribute("loginUser");
		List<BuyListDTO> goodsBuyinfo = gservice.getBuygoods(userid);
		model.addAttribute("goodsBuyinfo", goodsBuyinfo);
	    
//	    장바구니 테이블을 굿즈넘버에 따라 그룹핑
		Map<Integer, List<BuyListDTO>> goodsBuyInfoBygoodsnum = goodsBuyinfo.stream().collect(Collectors.groupingBy(BuyListDTO::getGoodsnum));
		model.addAttribute("MgoodsbuyInfo", goodsBuyInfoBygoodsnum);
		
//	    해당 굿즈 정보 가지고오기
	    List<GoodsDTO> goodsInfo = new ArrayList<>();
	    for(BuyListDTO buy : goodsBuyinfo) {
	    	// 장바구니 굿즈넘버 가져와서
	    	int goodsnum = buy.getGoodsnum();	    	
			
			boolean check = false;
			 
			for (GoodsDTO goods : goodsInfo) {
				if (goods.getGoodsnum() == goodsnum){
					check = true;
					break;
				}
			}
			if (!check) {
			goodsInfo.add(gservice.getgoodsBycart(goodsnum)); 
	    }
	    }
	    model.addAttribute("goodsInfo", goodsInfo);    
	    
		return "mypage/mypage_buy";
	}
	
	@PostMapping("mypage_buy")
	public void mypage_buy(int goodsnum, String userid, String size, int quantity) {
		gservice.putBuy(goodsnum, userid, size, quantity);
	}
	
	@PostMapping("delete_wish")
	// 스프링 MVC는 기본적으로 컨트롤러클래스 안에서 메소드가 void로 반환되면
	// 뷰를 찾으려고함 > 실제로 해당 템플릿이 없으면 DB처리는 제대로 되지만 500에러가남
	// putbuy는 실제로 mypage_buy라는 템플릿이 있어서 에러가 나지 않은것
	// ResponseEntity를 달아주고 성공값이 200을 return 해줘야함
	public ResponseEntity<Void> delete_wish(int wishnum) {
		gservice.deleteWish(wishnum);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("deletebuy")
	public ResponseEntity<Void> deletebuy(int buynum) {
		gservice.deleteBuy(buynum);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("mypage_buy_modify")
	public ResponseEntity<Void> mypage_buy_modify(int goodsnum, String userid, String size, int quantity, int buynum) {
		gservice.putBuy_modify(goodsnum, userid, size, quantity, buynum);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("getwishnumBygoodsnum")
	@ResponseBody
	public Integer getwishnumBygoodsnum(int goodsnum, String userid) {
		Integer wishnum = gservice.getwishnumBygoodsnum(goodsnum, userid);
		System.out.println("위시넘"+wishnum);
		
		if (wishnum == 0 || wishnum==null) {
	        return 0;
	    }
		return wishnum;
	}
	
	@PostMapping("getbuynumBygoodsnum")
	@ResponseBody
	public Integer getbuynumBygoodsnum(int goodsnum, String userid, String size) {
		Integer buynum = gservice.getbuynumBygoodsnum(goodsnum, userid, size);
		 System.out.println("buynum: " + buynum);
		 
		if (buynum == 0 || buynum==null) {
	        return 0;
	    }
		return buynum;
	}
	
}
