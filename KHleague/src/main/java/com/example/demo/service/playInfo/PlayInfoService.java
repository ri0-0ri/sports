package com.example.demo.service.playInfo;

import java.util.List;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import com.example.demo.model.dto.GEndBoard.GEndBoardDTO;

public interface PlayInfoService {
	List<GWillBoardDTO> getUpcomingGames(); // 경기 예정 데이터

	List<GEndBoardDTO> getEndedGames(); // 경기 종료 데이터

	public List<GWillBoardDTO> getGamesByMonth(int month); // 월별 경기 예정 데이터 조회

}
