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
import com.example.demo.model.event.EboardDTO;
import com.example.demo.model.event.EventDTO;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.model.payment.OrderListDTO;
import com.example.demo.service.event.EventService;
import com.example.demo.service.gWillBoard.GWillBoardService;

@Controller
@RequestMapping("event/*")
public class EventContorller {
	
	@Autowired
	EventService eservice;
	
	@Autowired
	GWillBoardService gwservice;
	
	@GetMapping("notice")
	public void notice(Model model) {
		List<EboardDTO> eboardlist = eservice.geteboard();
		model.addAttribute("eboardlist", eboardlist);
		System.out.println(eboardlist);
		
		// 2. 각 EboardDTO에 대해 연관된 GWillBoardDTO를 가져옴
		for (EboardDTO eboard : eboardlist) {
			// 2-1. 각 게시글의 eventnum을 통해 EventDTO를 가져옴
			int eventnum = eboard.getEventnum();
			EventDTO event = eservice.geteventByeventnum(eventnum);
			if (event != null) {
				// 2-2. EventDTO에서 gWnum을 얻고, 그 값을 통해 GWillBoardDTO 리스트를 가져옴
				int gwnum = event.getGwnum();
				GWillBoardDTO game = gwservice.getgame(gwnum); // gwnum으로 관련 게임 목록 찾기

				// 2-3. 모델에 gamelist를 추가
				eboard.setGame(game); // EboardDTO에 gamelist 세팅
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
		eservice.puteboard(eboard);			
		return "redirect:/event/notice";			
	}
	
	@PostMapping("updateboard")
	public String updateboard(EboardDTO eboard) {
		System.out.println(eboard);
		eservice.updateeboard(eboard);
		return "redirect:/event/notice";			
	}
	
	public void makewinner() {
		// 
		// 경기종료 게시판, 경기종료된 fboard
	}
	
}
