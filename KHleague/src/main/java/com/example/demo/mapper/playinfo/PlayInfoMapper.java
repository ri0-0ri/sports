package com.example.demo.mapper.playinfo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;

@Mapper
public interface PlayInfoMapper {

	// 경기 데이터 가져오기
	@Select("SELECT * FROM g_will_board ORDER BY gWdate")
	List<GWillBoardDTO> getUpcomingGames();

	// 월별 경기 예정 데이터 가져오기
	@Select("SELECT * FROM g_will_board WHERE MONTH(gWdate) = #{month} ORDER BY gWdate")
	List<GWillBoardDTO> getGamesByMonth(int month); // 월별 경기 예정 데이터 조회

	@Select("SELECT * FROM g_will_board WHERE EXTRACT(MONTH FROM gwtime) = #{month} ORDER BY gWnum DESC")
	List<GWillBoardDTO> getUpcomingGamesByMonth(int month); // 월별 경기 데이터를 조회하는 쿼리 추가

}
