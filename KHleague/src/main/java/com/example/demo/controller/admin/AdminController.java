package com.example.demo.controller.admin;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import com.example.demo.model.event.EventDTO;
import com.example.demo.model.teamDTO.TeamDTO;
import com.example.demo.service.team.TeamService;
import com.example.demo.service.event.EventService;
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
	
	@Autowired
	private EventService eservice;

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
	
	@GetMapping("admin_event")
	public void admin_event(Model model) {
		List<TeamDTO> teams = teamService.getTeams();
		model.addAttribute("teams", teams);
		List<GWillBoardDTO> games = gWillBoardService.getgWillList();
		model.addAttribute("games", games);
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
	
	@PostMapping("add_event")
	public String add_event(@RequestParam int game, String event, String item) {
		System.out.println("게임 : "+game+" / 이벤트 : "+event+" / 아이템 : "+item);
		
		EventDTO events = new EventDTO();
		events.setGwnum(game);
		events.setEventtype(event);
		events.setEventitem(item);
		eservice.putevent(events);
		
		return "redirect:/admin/admin_event";
	}

	@GetMapping("getList")
	public ResponseEntity<List<GWillBoardDTO>> getgWillList() {
		List<GWillBoardDTO> boardList = gWillBoardService.getgWillList();
		// 데이터 확인용 로그 출력
		return ResponseEntity.ok(boardList);
	}
	
	@GetMapping("getevent")
	public ResponseEntity<List<EventDTO>> getevent(){
		List<EventDTO> eventlist = eservice.geteventlist();
		System.out.println(eventlist);
		return ResponseEntity.ok(eventlist);
	}

	// 경기 일정 취소
	@PostMapping("cancel_schedule")
	public String cancelSchedule(@RequestParam int gWnum) {
		gWillBoardService.deleteGWillBoard(gWnum); // 해당 일정 삭제
		return "redirect:/admin/admin_time"; // 취소 후 일정 목록 페이지로 리다이렉트
	}
	
	@PostMapping("deleteevent")
	public ResponseEntity deleteevent(@RequestParam int eventnum) {
		System.out.println("이벤트넘버받아온것 "+eventnum);
		eservice.deleteevent(eventnum);
		return ResponseEntity.ok("이벤트 삭제 완료!");
	}
	
	@PostMapping("deleteebaord")
	public ResponseEntity deleteeboard(@RequestParam int eboardnum) {
		System.out.println("이벤트보드넘버받아온것 "+eboardnum);
		eservice.deleteeboard(eboardnum);
		return ResponseEntity.ok("게시글 삭제 완료!");
	}
	
	@GetMapping("admin_makeevent")
	public void makeevent(Model model) {
		List<EventDTO> eventlist = eservice.geteventlist();
		model.addAttribute("eventlist", eventlist);
	}
	
	@PostMapping("pluscount")
	public ResponseEntity pluscount(@RequestParam int eboardnum) {
		eservice.updatecount(eboardnum);
		System.out.println("조회수증가함");
		return ResponseEntity.ok("조회수 증가 완료!");
	}
}
