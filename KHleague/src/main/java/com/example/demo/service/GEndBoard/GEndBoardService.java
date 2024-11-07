package com.example.demo.service.GEndBoard;

import com.example.demo.model.dto.GEndBoard.GEndBoardDTO;

public interface GEndBoardService {
	  // 경기 종료 기록 추가
    void addGEndBoard(GEndBoardDTO gEndBoardDTO);
}
