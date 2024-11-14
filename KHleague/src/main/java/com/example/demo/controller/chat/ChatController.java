package com.example.demo.controller.chat;

import com.example.demo.model.chat.ChatDTO;
import com.example.demo.service.chat.ChatService;
import com.example.demo.service.fighting.FightingService;
import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/chat")
public class ChatController {

	private final ChatService chatService;
	private final FightingService fightingService; // FightingService를 주입

	@Autowired
	public ChatController(ChatService chatService, FightingService fightingService) {
		this.chatService = chatService;
		this.fightingService = fightingService; // 주입
	}

	@PostMapping("/sendMessage")
	public ResponseEntity<?> sendMessage(@RequestBody ChatDTO chatDTO, HttpSession session) {
		String loggedInUserId = (String) session.getAttribute("loginUser");

		if (loggedInUserId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("{\"success\": false, \"message\": \"로그인된 사용자만 메시지를 보낼 수 있습니다.\"}");
		}

		try {
			// 최근 경기 가져오기
			List<GWillBoardDTO> top3Fights = fightingService.getTop3FightingSchedules();
			if (top3Fights.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("{\"success\": false, \"message\": \"최근 경기 정보가 없습니다.\"}");
			}
			int latestGWnum = top3Fights.get(0).getGWnum(); // 가장 최근 경기의 gWnum 가져오기

			// 세션에서 사용자 ID를 가져와서 ChatDTO에 설정
			chatDTO.setUserId(loggedInUserId);
			chatDTO.setGWnum(latestGWnum); // 최근 경기의 gWnum 설정

			// 메시지를 DB에 저장
			chatService.saveChatMessage(chatDTO);

			return ResponseEntity.ok().body("{\"success\": true}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"success\": false, \"message\": \"메시지 전송에 실패했습니다.\"}");
		}
	}

	@GetMapping("/getMessages")
	public ResponseEntity<?> getMessages() {
		try {
			// 전체 채팅 메시지를 가져옴
			List<ChatDTO> messages = chatService.getAllChatMessages();
			return ResponseEntity.ok(messages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"success\": false, \"message\": \"메시지를 불러오는 데 실패했습니다.\"}");
		}
	}
}
