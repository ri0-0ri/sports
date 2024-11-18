package com.example.demo.controller.goods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.chat.ChatDTO;
import com.example.demo.model.event.EboardDTO;
import com.example.demo.model.event.EventDTO;
import com.example.demo.model.goods.Criteria;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.service.event.EventService;
import com.example.demo.service.goods.GoodsService;
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
	
//	public EboardDTO getUniqueEboardByEventnum(int eventnum) {
//	    List<EboardDTO> eboardList = eservice.geteboardlistbyeventnum(eventnum);
//	    // 중복 제거를 위해 Set을 사용
//	    Set<Integer> seenEventNums = new HashSet<>();
//	    EboardDTO uniqueEboards = new EboardDTO();
//	    for (EboardDTO eboard : eboardList) {
//	        if (!seenEventNums.contains(eboard.getEventnum())) {
//	            seenEventNums.add(eboard.getEventnum());
//	            uniqueEboards = eboard;
//	        }
//	    }
//	    return uniqueEboards;
//	}
	
	@GetMapping("prize")
	public void prize(Model model, HttpSession session) {
		String loginUser = (String)session.getAttribute("loginUser");
		String winnerlist = uservice.findUserById(loginUser).getWinnerevent();
		System.out.println("winnerlist : "+winnerlist);
		String[] winner_array = winnerlist.split("\\/\\/");
		
		// 이벤트와 적립금 담아갈것
		Map<EboardDTO, String> rewards = new HashMap<>();
		// 굿즈 담아갈것
		Map<EboardDTO, GoodsDTO> goods = new HashMap<>();
		
		// 중복제거위한 해시샛
		Set<Integer> processedEventNums = new HashSet<>();
		
		for(int i=0; i<winner_array.length; i++) {
			System.out.println("이벤트넘버 : " +winner_array[i]);
			int num = Integer.parseInt(winner_array[i]);		
			EventDTO event = eservice.geteventByeventnum(num);

	        // 이미 처리한 eventnum이라면 건너뛰기
	        if (processedEventNums.contains(num)) {
	            continue;
	        }
	        // 중복 처리 방지를 위해 처리한 이벤트 번호를 Set에 추가
	        processedEventNums.add(num);
	        // EboardDTO 가져오기
	        List<EboardDTO> eboardList = eservice.geteboardlistbyeventnum(num);
	        EboardDTO eboard = eboardList.get(eboardList.size() - 1);
			
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
		model.addAttribute("rewards", rewards);
		model.addAttribute("goods", goods);
		System.out.println(goods);		
	}
}
