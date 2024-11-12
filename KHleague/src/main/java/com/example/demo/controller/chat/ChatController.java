package com.example.demo.controller.chat;

import com.example.demo.model.chat.ChatDTO;
import com.example.demo.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/chat")
public class ChatController {

	private final ChatService chatService;

	@Autowired
	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}

	@PostMapping("/sendMessage")
	public ResponseEntity<?> sendMessage(@RequestBody ChatDTO chatDTO, HttpSession session) {
		String loggedInUserId = (String) session.getAttribute("loginUser");

		if (loggedInUserId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("{\"success\": false, \"message\": \"로그인된 사용자만 메시지를 보낼 수 있습니다.\"}");
		}

		try {
			// 세션에서 사용자 ID를 가져와서 ChatDTO에 설정
			chatDTO.setUserId(loggedInUserId);

			// 메시지를 DB에 저장
			chatService.saveChatMessage(chatDTO);

			return ResponseEntity.ok().body("{\"success\": true}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"success\": false, \"message\": \"메시지 전송에 실패했습니다.\"}");
		}
	}
}
