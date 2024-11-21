package com.example.demo.mapper.team;

import com.example.demo.model.teamDTO.PlayerDTO;
import com.example.demo.model.teamDTO.TeamDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    List<TeamDTO> getAllTeams(); // 팀 정보를 가져오는 메서드 선언

	List<PlayerDTO> getplayers(int teamnum);
}
