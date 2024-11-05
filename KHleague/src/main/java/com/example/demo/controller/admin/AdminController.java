package com.example.demo.controller.admin;

import com.example.demo.model.teamDTO.TeamDTO;
import com.example.demo.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	private TeamService teamService;

	@GetMapping("admin_time")
	public String showAdmin_time(Model model) {
		List<TeamDTO> teams = teamService.getTeams(); // 팀 정보 가져오기
		model.addAttribute("teams", teams); // 모델에 추가
		return "admin/admin_time"; // 팀 정보를 보여줄 HTML 페이지로 리턴
	}

	@GetMapping("admin_user")
	public String showAdmin_user() {
		return "admin/admin_user";
	}
}
