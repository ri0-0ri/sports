package com.example.demo.service.main;

import com.example.demo.mapper.main.HomeMapper;
import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

	@Autowired
	private HomeMapper homeMapper;

	public List<GWillBoardDTO> getUpcomingMatches() {
		return homeMapper.getUpcomingMatches();
	}
}
