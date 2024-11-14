package com.example.demo.mapper.fighting;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FightingMapper {
	List<GWillBoardDTO> getTop3FightingSchedules(); // 최신 3경기 일정 조회
}
