package com.example.demo.controller.admin;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import com.example.demo.model.chat.ChatDTO;
import com.example.demo.model.event.EboardDTO;
import com.example.demo.model.event.EventDTO;
import com.example.demo.model.teamDTO.TeamDTO;
import com.example.demo.service.team.TeamService;
import com.example.demo.service.GameScheduleService.GameScheduleService;
import com.example.demo.service.chat.ChatService;
import com.example.demo.service.event.EventService;
import com.example.demo.service.gWillBoard.GWillBoardService;
import com.example.demo.service.playInfo.PlayInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	private TeamService teamService;

	@Autowired
	private GWillBoardService gWillBoardService;
	
	@Autowired
	private EventService eservice;
	
	@Autowired
	private ChatService cservice;
	
	@Autowired
	private GameScheduleService gservice;

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
		List<EventDTO> eventlists = eservice.geteventlist();
		List<EventDTO> eventlist = new ArrayList<>();
		// 경기가 끝난 이벤트만 추가해주기
		for(EventDTO event : eventlists) {
			int gwnum = event.getGwnum();
			// 끝난 팀 조회
			List<GWillBoardDTO> endgames = gservice.getEndgame();
			for(GWillBoardDTO endgame : endgames) {
				if(gwnum==endgame.getGWnum()) {
					eventlist.add(event);
					break;
				}
			}
		}
		model.addAttribute("eventlist", eventlist);
		
		Map<Integer, List<ChatDTO>> chatMap = new HashMap<>();
		for(EventDTO event : eventlist) {
			int gwnum = event.getGwnum();
			// 끝난 팀 조회
			List<GWillBoardDTO> endgames = gservice.getEndgame();
			
			// 이벤트 타입이 룰렛이면(이긴 팀만 추첨), 댓글 중복가능(댓글 많이쓸수록 좋음)
			if(event.getEventtype().equals("룰렛")) {
				// 이긴 팀 조회 + 채팅 가져오기
				int chat_type = 0;
				for(GWillBoardDTO endgame : endgames) {
					int score1 = endgame.getTeam1score();
					int score2 = endgame.getTeam2score();
					if(score1>score2) {
						// 1팀이 이기면 1번 채팅만 가져오게
						chat_type = 1;
					}
					else if(score1<score2) {
						chat_type = 2;
						// 2팀이 이기면 2번 채팅만 가져오게
					}
				}			
				List<ChatDTO> chatlist = cservice.getchatBygwnum(gwnum, chat_type);
				chatMap.put(gwnum, chatlist);				
			}
			// 이벤트 타입이 단어 맞추기면(모든 사람들 중 맞춘 사람들만 추첨), 중복 불가능(id당 한번만)
			else if(event.getEventtype().equals("단어 맞추기")) {
				int eventnum = event.getEventnum();
				EboardDTO eboard = eservice.geteboardbyeventnum(eventnum);	
				String str = eboard.getEventcon();				
				List<ChatDTO> chatlist = cservice.getchatBystr(gwnum, str);
				
		        // 중복 확인을 위한 Set을 사용 > hashset은 중복값 자동으로 걸러줌(얘는 확인용)
	            Set<String> checkuser = new HashSet<>();
	            List<ChatDTO> filteredChatList = new ArrayList<>(); // 중복되지 않은 chatlist(얘는 실제 넣을 dto)	            

	            for (ChatDTO chat : chatlist) {
	                String id = chat.getUserId();	                
	                // 이미 해당 아이디가 checkuser에 존재하면 중복된 것
	                if (!checkuser.contains(id)) {
	                    checkuser.add(id); // 중복되지 않으면 확인용 checkuser에 추가
	                    filteredChatList.add(chat); // 중복되지 않은 ChatDTO만 추가
	                }
	            }
	            
	            // 중복을 제외한 filteredChatList를 chatMap에 저장
	            chatMap.put(gwnum, filteredChatList);
	            System.out.println("단어 맞추기: " + filteredChatList);						
			}		
		}
		model.addAttribute("chatMap", chatMap);
	}
	
	@PostMapping("pluscount")
	public ResponseEntity pluscount(@RequestParam int eboardnum) {
		eservice.updatecount(eboardnum);
		System.out.println("조회수증가함");
		return ResponseEntity.ok("조회수 증가 완료!");
	}

}
