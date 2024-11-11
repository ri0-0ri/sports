package com.example.demo.service.playInfo;

import java.util.List;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import com.example.demo.model.dto.GEndBoard.GEndBoardDTO;

public interface PlayInfoService {
	List<GWillBoardDTO> getUpcomingGames(); // 경기 예정 데이터

	List<GEndBoardDTO> getEndedGames(); // 경기 종료 데이터

	// 월별 경기 예정 데이터를 가져오는 메서드 추가
	List<GWillBoardDTO> getUpcomingGamesByMonth(int month); // 월별 경기 예정 데이터

	// 월별 경기 종료 데이터를 가져오는 메서드 추가
	List<GEndBoardDTO> getEndedGamesByMonth(int month); // 월별 경기 종료 데이터
}
