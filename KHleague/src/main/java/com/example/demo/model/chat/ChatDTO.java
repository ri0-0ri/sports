package com.example.demo.model.chat;

import lombok.Data;

@Data
public class ChatDTO {
	private String userId; // 사용자 ID
	private String content; // 채팅 내용
	private int chatType; // 1 = 왼쪽 채팅, 2 = 오른쪽 채팅
	private int gWnum; // 경기 번호 (g_will_board의 gWnum 참조)
}
