package com.example.demo.controller.fighting;

import com.example.demo.service.fighting.FightingService;
import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/fighting/")
public class FightingController {

	@Autowired
	private FightingService fightingService;

	@GetMapping("fighting")
	public String showFightingPage(Model model) {
		List<GWillBoardDTO> top3Fights = fightingService.getTop3FightingSchedules();
		model.addAttribute("top3Fights", top3Fights); // 데이터 전달
		return "fighting/fighting"; // JSP 이름
	}
}
