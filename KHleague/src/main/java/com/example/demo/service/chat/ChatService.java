package com.example.demo.service.chat;

import com.example.demo.mapper.chat.ChatMapper;
import com.example.demo.model.chat.ChatDTO;

import java.util.List;

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
	public List<ChatDTO> getAllChatMessages() {
	    return chatMapper.getAllChatMessages();
	}

	public List<ChatDTO> getchatBygwnum(int gwnum, int chat_type) {
		return chatMapper.getchatBygwnum(gwnum, chat_type);
	}

	public List<ChatDTO> getchatBystr(int gwnum, String str) {
		return chatMapper.getchatBystr(gwnum, str);
	}

}
