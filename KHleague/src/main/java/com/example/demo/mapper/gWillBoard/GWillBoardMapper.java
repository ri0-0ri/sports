package com.example.demo.mapper.gWillBoard;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;

@Mapper
public interface GWillBoardMapper {
	@Insert("INSERT INTO g_will_board (gWdate, team1name, team2name, gwtime) VALUES (#{gWdate}, #{team1name}, #{team2name}, #{gwtime})")
	void insertGWillBoard(GWillBoardDTO gWillBoardDTO);
	
	List<GWillBoardDTO> getgWillList();
	@Delete("DELETE FROM g_will_board WHERE gWnum = #{gWnum}")
    void deleteGWillBoard(int gWnum); // 삭제 쿼리 추가
}
