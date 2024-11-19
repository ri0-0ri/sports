package com.example.demo.controller.goods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.UserDTO.UserDTO;
import com.example.demo.model.chat.ChatDTO;
import com.example.demo.model.event.EboardDTO;
import com.example.demo.model.event.EventDTO;
import com.example.demo.model.goods.Criteria;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.model.moneyDTO.MoneyDTO;
import com.example.demo.service.event.EventService;
import com.example.demo.service.goods.GoodsService;
import com.example.demo.service.money.MoneyService;
import com.example.demo.service.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("goods/*")
public class GoodsController {
	
	@Autowired
	private GoodsService service;
	
	@Autowired
	private EventService eservice;
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	private GoodsService gservice;
	
	@Autowired
	MoneyService mservice;
	
	@GetMapping("goods")
	public void goods(Model model, HttpSession session, HttpServletRequest req, Criteria cri) {
		String loginUser = (String)session.getAttribute("loginUser");		

	    // view_type 파라미터를 제대로 가져오는지 확인
	    String viewType = req.getParameter("view_type");
	    if (viewType != null) {
	        System.out.println("view_type: " + viewType);  // 확인용 로그
	    } else {
	        System.out.println("view_type is null");
	    }

	    // view_type을 Criteria에 설정
	    cri.setView_type(viewType);
	    
		List<GoodsDTO> goodsList = service.getgoods(cri);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("cri", cri);
	}
	
	@GetMapping("prize")
	public void prize(Model model, HttpSession session) {
		// 경품내역 가져오기
		List<EventDTO> allevent = eservice.geteventlist();
		model.addAttribute("allevent", allevent);
		// 굿즈 가져오기
		List<GoodsDTO> goodsList = service.getgoods();
		model.addAttribute("goodsList", goodsList);
		
		String loginUser = (String)session.getAttribute("loginUser");
		String winnerlist = uservice.findUserById(loginUser).getWinnerevent();
		System.out.println("winnerlist : "+winnerlist);
		String[] winner_array = (winnerlist != null && !winnerlist.isEmpty()) ? winnerlist.split("\\/\\/") : new String[0];
		
		// 이벤트와 적립금 담아갈것
		Map<EboardDTO, String> rewards = new HashMap<>();
		// 수령완료한 이벤트와 적립금 담아갈것
		Map<EboardDTO, String> getrewards = new HashMap<>();
		// 굿즈 담아갈것
		Map<EboardDTO, GoodsDTO> goods = new HashMap<>();
		// 수령완료한 굿즈 담아갈것
		Map<EboardDTO, GoodsDTO> getgoods = new HashMap<>();
		
		// 중복제거위한 해시샛
		Set<Integer> processedEventNums = new HashSet<>();
		
		for(int i=0; i<winner_array.length; i++) {
			System.out.println("이벤트넘버 : " +winner_array[i]);
			
			// 이미 수령완료한 제품이면?
			if(winner_array[i].contains("-")) {
				int num = Integer.parseInt(winner_array[i].replace("-", "").trim());
				EventDTO event = eservice.geteventByeventnum(num);
				
				// 이미 처리한 eventnum이라면 건너뛰기
				if (processedEventNums.contains(num)) {
					continue;
				}
				// 중복 처리 방지를 위해 처리한 이벤트 번호를 Set에 추가 + 이벤트보드 가져오기(이벤트제목 띄워줘야함)
				processedEventNums.add(num);
				List<EboardDTO> eboardList = eservice.geteboardlistbyeventnum(num);
				EboardDTO eboard = eboardList.get(eboardList.size() - 1);
				
				// 아이템 타입 처리
				String eventitem = event.getEventitem();
				// 아이템이 적립금이면
				if(eventitem.contains("적립금")) {
					getrewards.put(eboard,eventitem);
				}
				else {				
					// 아이템이 굿즈면
					GoodsDTO goodsdto = gservice.getgoodsbygoodsname(eventitem);
					getgoods.put(eboard, goodsdto);
				}
			}
			else {				
				int num = Integer.parseInt(winner_array[i]);		
				EventDTO event = eservice.geteventByeventnum(num);
				
				// 이미 처리한 eventnum이라면 건너뛰기
				if (processedEventNums.contains(num)) {
					continue;
				}
				// 중복 처리 방지를 위해 처리한 이벤트 번호를 Set에 추가 + 이벤트보드 가져오기(이벤트제목 띄워줘야함)
				processedEventNums.add(num);
				List<EboardDTO> eboardList = eservice.geteboardlistbyeventnum(num);
				EboardDTO eboard = eboardList.get(eboardList.size() - 1);
				
				// 아이템 타입 처리
				String eventitem = event.getEventitem();
				// 아이템이 적립금이면
				if(eventitem.contains("적립금")) {
					rewards.put(eboard,eventitem);
				}
				else {				
					// 아이템이 굿즈면
					GoodsDTO goodsdto = gservice.getgoodsbygoodsname(eventitem);
					goods.put(eboard, goodsdto);
				}
			}			
		}
		model.addAttribute("rewards", rewards);
		model.addAttribute("getrewards", getrewards);
		model.addAttribute("goods", goods);
		model.addAttribute("getgoods", getgoods);
		System.out.println(goods);		
	}
	
	@PostMapping("getReward")
	public ResponseEntity getReward(@RequestParam String userid, String userReward, String eventnum) {
		System.out.println(userid+userReward+eventnum);
		
		// 유저DTO 적립금 변경
		UserDTO user = uservice.findUserById(userid);
		int oldReward = user.getUserReward();
		int intreward = Integer.parseInt(userReward.replaceAll("[^0-9]", "").trim());
		String strreward = userReward.replaceAll("[^0-9]", "").trim();
		int newReward = oldReward +intreward;
		uservice.updateUserReward(newReward, userid);
		
		// 유저DTO winner 스트링 변경
		String newwinnerevent = user.getWinnerevent().replaceAll(eventnum,"-"+eventnum);
		uservice.putwinevent(newwinnerevent, userid);
		
		// moneyDTO 변경
		// 가입 적립금 5000원 추가
		MoneyDTO money = new MoneyDTO();
		money.setMoneytype("적립금");
		money.setUserid(userid);
		money.setMoneyname("이벤트 적립금");
		money.setChangeMoney("+"+strreward);
		mservice.putmoney(money);
		
		return ResponseEntity.ok("적립금은 3일간 유효합니다!");	
	}
}
