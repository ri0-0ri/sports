package com.example.demo.mapper.main;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeMapper {
	// 가장 이른 3개의 경기 가져오기
	List<GWillBoardDTO> getUpcomingMatches();
}
