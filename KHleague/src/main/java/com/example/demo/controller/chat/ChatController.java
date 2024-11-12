package com.example.demo.controller.chat;

import com.example.demo.model.chat.ChatDTO;
import com.example.demo.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

	private final ChatService chatService;

	@Autowired
	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}

	@PostMapping("/sendMessage")
	public ResponseEntity<?> sendMessage(@RequestBody ChatDTO chatDTO) {
		try {
			chatService.saveChatMessage(chatDTO); // DB에 저장
			return ResponseEntity.ok().body("{\"success\": true}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"success\": false, \"message\": \"메시지 전송에 실패했습니다.\"}");
		}
	}
}
