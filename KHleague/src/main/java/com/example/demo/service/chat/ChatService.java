package com.example.demo.service.chat;

import com.example.demo.mapper.chat.ChatMapper;
import com.example.demo.model.chat.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private final ChatMapper chatMapper;

	@Autowired
	public ChatService(ChatMapper chatMapper) {
		this.chatMapper = chatMapper;
	}

	public void saveChatMessage(ChatDTO chatDTO) {
		chatMapper.insertChatMessage(chatDTO); // DB에 채팅 메시지 저장
	}
}
