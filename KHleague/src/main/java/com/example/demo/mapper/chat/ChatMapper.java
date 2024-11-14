package com.example.demo.mapper.chat;

import com.example.demo.model.chat.ChatDTO;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChatMapper {

	// 채팅 메시지 삽입 (gWnum을 함께 삽입)
	@Insert("INSERT INTO fboard (user_id, content, chat_type, gWnum) VALUES (#{userId}, #{content}, #{chatType}, #{gWnum})")
	void insertChatMessage(ChatDTO chatDTO);

	// 모든 채팅 메시지 가져오기
	@Select("SELECT * FROM fboard ORDER BY id")
	List<ChatDTO> getAllChatMessages();
}
