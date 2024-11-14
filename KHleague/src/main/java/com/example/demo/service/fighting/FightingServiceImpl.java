package com.example.demo.service.fighting;

import com.example.demo.mapper.fighting.FightingMapper;
import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FightingServiceImpl implements FightingService {

	@Autowired
	private FightingMapper fightingMapper;

	@Override
	public List<GWillBoardDTO> getTop3FightingSchedules() {
		return fightingMapper.getTop3FightingSchedules(); // Mapper 호출
	}
}
