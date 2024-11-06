package com.example.demo.controller.admin;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import com.example.demo.model.teamDTO.TeamDTO;
import com.example.demo.service.team.TeamService;
import com.example.demo.service.gWillBoard.GWillBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@GetMapping("getList")
	public ResponseEntity<List<GWillBoardDTO>> getgWillList() {
		List<GWillBoardDTO> boardList = gWillBoardService.getgWillList();
		// 데이터 확인용 로그 출력
		return ResponseEntity.ok(boardList);
	}

	// 경기 일정 취소
	@PostMapping("cancel_schedule")
	public String cancelSchedule(@RequestParam int gWnum) {
		gWillBoardService.deleteGWillBoard(gWnum); // 해당 일정 삭제
		return "redirect:/admin/admin_time"; // 취소 후 일정 목록 페이지로 리다이렉트
	}
}
