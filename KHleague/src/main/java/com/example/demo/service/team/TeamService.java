package com.example.demo.service.team;

import com.example.demo.mapper.team.TeamMapper;
import com.example.demo.model.teamDTO.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
	@Autowired
	private TeamMapper teamMapper;

	public List<TeamDTO> getTeams() {
		return teamMapper.getAllTeams(); // 데이터베이스에서 모든 팀 정보 가져오기
	}
}
