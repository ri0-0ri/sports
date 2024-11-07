package com.example.demo.service.playInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.playinfo.PlayInfoMapper;
import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import com.example.demo.model.dto.GEndBoard.GEndBoardDTO;

@Service
public class PlayInfoServiceImpl implements PlayInfoService {

	@Autowired
	private PlayInfoMapper playInfoMapper;

	@Override
	public List<GWillBoardDTO> getUpcomingGames() {
		return playInfoMapper.getUpcomingGames();
	}

	@Override
	public List<GEndBoardDTO> getEndedGames() {
		return playInfoMapper.getEndedGames();
	}
}
