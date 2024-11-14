package com.example.demo.service.fighting;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;

import java.util.List;

public interface FightingService {
	List<GWillBoardDTO> getTop3FightingSchedules(); // 최신 3경기 일정 가져오기
}
