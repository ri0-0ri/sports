package com.example.demo.service.GEndBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.GEndBoard.GEndBoardMapper;
import com.example.demo.model.dto.GEndBoard.GEndBoardDTO;

@Service
public class GEndBoardServiceImpl implements GEndBoardService {
	@Autowired
	private GEndBoardMapper gEndBoardMapper;

	@Override
	public void addGEndBoard(GEndBoardDTO gEndBoardDTO) {
		gEndBoardMapper.insertGEndBoard(gEndBoardDTO); // 종료된 경기 기록 추가
	}

}
