package com.example.demo.controller.main;

import com.example.demo.model.event.EboardDTO;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.service.event.EventService;
import com.example.demo.service.goods.GoodsService;
import com.example.demo.service.main.HomeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;
	
	@Autowired
	EventService eservice;
	
	@Autowired
	GoodsService gservice;

	@GetMapping("/")
	public String Home(HttpSession session, Model model) {
		String loginUser = (String) session.getAttribute("loginUser");
		model.addAttribute("isLoggedIn", loginUser != null);

		// 경기 정보 가져오기
		model.addAttribute("upcomingMatches", homeService.getUpcomingMatches());
		
		List<EboardDTO> eboardlist = eservice.geteboard();
		model.addAttribute("eboardlist", eboardlist);
		System.out.println(eboardlist);
		
		List<GoodsDTO> goodsList = gservice.getgoods();
		model.addAttribute("goodsList", goodsList);

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
