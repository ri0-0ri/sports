package com.example.demo.service.playInfo;

import java.util.List;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;

public interface PlayInfoService {
	List<GWillBoardDTO> getUpcomingGames(); // 경기데이터

	// 월별 경기 예정 데이터를 가져오는 메서드 추가
	List<GWillBoardDTO> getUpcomingGamesByMonth(int month); // 월별 경기 예정 데이터

	}
