package com.example.demo.service.playInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.playinfo.PlayInfoMapper;
import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;

@Service
public class PlayInfoServiceImpl implements PlayInfoService {

	@Autowired
	private PlayInfoMapper playInfoMapper;

	@Override
	public List<GWillBoardDTO> getUpcomingGames() {
		return playInfoMapper.getUpcomingGames();
	}

	@Override
	public List<GWillBoardDTO> getUpcomingGamesByMonth(int month) {
		return playInfoMapper.getUpcomingGamesByMonth(month); // 월별 경기 데이터 조회
	}

}
