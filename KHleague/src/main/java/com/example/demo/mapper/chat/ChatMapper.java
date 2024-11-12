package com.example.demo.mapper.chat;

import com.example.demo.model.chat.ChatDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {

	@Insert("INSERT INTO fboard (user_id, content, chat_type) VALUES (#{userId}, #{content}, #{chatType})")
	void insertChatMessage(ChatDTO chatDTO);
}
