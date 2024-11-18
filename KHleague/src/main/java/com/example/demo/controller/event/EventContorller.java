package com.example.demo.controller.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import com.example.demo.model.UserDTO.UserDTO;
import com.example.demo.model.event.EboardDTO;
import com.example.demo.model.event.EventDTO;
import com.example.demo.model.goods.Criteria;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.model.payment.OrderListDTO;
import com.example.demo.service.event.EventService;
import com.example.demo.service.gWillBoard.GWillBoardService;
import com.example.demo.service.user.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("event/*")
public class EventContorller {
	
	@Autowired
	EventService eservice;
	
	@Autowired
	GWillBoardService gwservice;
	
	@Autowired
	UserService uservice;
	
	@GetMapping("notice")
	public void notice(Model model, HttpServletRequest req, Criteria cri) {
	    String viewType = req.getParameter("view_type");
	    if (viewType != null) {
	        System.out.println("view_type: " + viewType);  // 확인용 로그
	    } else {
	        System.out.println("view_type is null");
	    }

	    cri.setView_type(viewType);
		
		List<EboardDTO> eboardlist = eservice.geteboard(cri);
		model.addAttribute("eboardlist", eboardlist);
		
		// 2. 각 EboardDTO에 대해 연관된 GWillBoardDTO를 가져옴
		for (EboardDTO eboard : eboardlist) {
			// 2-1. 각 게시글의 eventnum을 통해 EventDTO를 가져옴
			int eventnum = eboard.getEventnum();
			EventDTO event = eservice.geteventByeventnum(eventnum);
			System.out.println("위너 : "+event.getWinner());
			if (event != null) {
				// 2-2. EventDTO에서 gWnum을 얻고, 그 값을 통해 GWillBoardDTO 리스트를 가져옴
				int gwnum = event.getGwnum();
				GWillBoardDTO game = gwservice.getgame(gwnum); // gwnum으로 관련 게임 목록 찾기

				// 2-3. 모델에 gamelist를 추가
				eboard.setGame(game);
			}
		}
	}
	
	@GetMapping("write")
	public void write(@RequestParam int eventnum, Model model) {
		EventDTO event = eservice.geteventByeventnum(eventnum);
		model.addAttribute("event", event);
		System.out.println(event);
		
		GWillBoardDTO game = gwservice.getgame(event.getGwnum());
		model.addAttribute("game", game);
		System.out.println(game);
	}
	
	@GetMapping("modify")
	public void modify(@RequestParam int eboardnum, Model model) {
		EboardDTO eboard = eservice.geteboardByeboardnum(eboardnum);
		model.addAttribute("eboard", eboard);
		
		int eventnum = eboard.getEventnum();
		
		EventDTO event = eservice.geteventByeventnum(eventnum);
		model.addAttribute("event", event);
		
		GWillBoardDTO game = gwservice.getgame(event.getGwnum());
		model.addAttribute("game", game);
	}
	
	@PostMapping("addboard")
	public String addboard(EboardDTO eboard) {
		System.out.println(eboard);
		eservice.puteboard(eboard);
		int eventnum = eboard.getEventnum();
		String str = eboard.getStr();
		eservice.updateevent(eventnum, str);
		return "redirect:/event/notice";			
	}
	
	@PostMapping("updateboard")
	public String updateboard(EboardDTO eboard) {
		System.out.println(eboard);
		eservice.updateeboard(eboard);
		int eventnum = eboard.getEventnum();
		String str = eboard.getStr();
		eservice.updateevent(eventnum, str);
		return "redirect:/event/notice";			
	}	
	
	@PostMapping("updatewinner")
	public ResponseEntity updatewinner(@RequestParam int eventnum, String winner) {
		eservice.updatewinner(eventnum, winner);
		// 이제 당첨된 유저에게 보상 넣어주기
		UserDTO user = uservice.findUserById(winner);
		String userevents = user.getWinnerevent();
		// 문자열 만들어서 보내기
		String neweventstr = Integer.toString(eventnum);	
		// 이미 당첨된 내역 있으면
	    if (userevents != null && !userevents.isEmpty()) {
	        // 기존 문자열에 //로 연결하여 추가
	        neweventstr = userevents + "//" + neweventstr;
	    }
	    System.out.println("새로운 유저스트링 : "+neweventstr);
		uservice.putwinevent(neweventstr, winner);
		return ResponseEntity.ok("당첨자 추가 완료");
	}
	
	@PostMapping("updatenowinner")
	public ResponseEntity updatenowinner(@RequestParam int eventnum, String winner) {
		eservice.updatewinner(eventnum, winner);
		return ResponseEntity.ok("당첨자 추가 완료");
	}
	
}
