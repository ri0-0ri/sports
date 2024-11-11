package com.example.demo.model.chat;

import lombok.Data;

@Data
public class ChatDTO {
	private int id;
	private String userId; // 채팅 작성자 아이디
	private String content; // 채팅 내용
	private String createdAt; // 메시지 작성 시간
	private int teamId; // 팀 구분 (팀1 or 팀2)
}
