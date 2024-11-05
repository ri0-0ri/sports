package com.example.demo.controller.admin;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import com.example.demo.model.teamDTO.TeamDTO;
import com.example.demo.service.team.TeamService;
import com.example.demo.service.gWillBoard.GWillBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	private TeamService teamService;

	@Autowired
	private GWillBoardService gWillBoardService;

	@GetMapping("admin_time")
	public String showAdmin_time(Model model) {
		List<TeamDTO> teams = teamService.getTeams();
		model.addAttribute("teams", teams);
		return "admin/admin_time";
	}

	@GetMapping("admin_user")
	public String showAdmin_user() {
		return "admin/admin_user";
	}

	@PostMapping("add_schedule")
	public String scheduleTeams(@RequestParam String team1, @RequestParam String team2, @RequestParam String date,
			@RequestParam String time) {
		GWillBoardDTO gWillBoardDTO = new GWillBoardDTO();
		LocalDateTime gWdate = LocalDateTime.parse(date + "T00:00:00");
		LocalDateTime gwtime = LocalDateTime.parse(date + "T" + time);

		gWillBoardDTO.setGWdate(gWdate);
		gWillBoardDTO.setTeam1name(team1); // 팀 이름 저장
		gWillBoardDTO.setTeam2name(team2); // 팀 이름 저장
		gWillBoardDTO.setGwtime(gwtime);

		gWillBoardService.addGWillBoard(gWillBoardDTO);

		return "redirect:/admin/admin_time"; // 알림 메시지 추가 필요
	}
}
