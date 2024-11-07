package com.example.demo.mapper.GEndBoard;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.dto.GEndBoard.GEndBoardDTO;


@Mapper
public interface GEndBoardMapper {
    // 경기 종료 기록 추가
    @Insert("INSERT INTO g_end_board (gEdate, team1score, team2score, team1name, team2name) " +
            "VALUES (#{gEdate}, #{team1score}, #{team2score}, #{team1name}, #{team2name})")
    void insertGEndBoard(GEndBoardDTO gEndBoardDTO);

    // 경기 일정 삭제
    @Delete("DELETE FROM g_will_board WHERE gWnum = #{gWnum}")
    void deleteGWillBoard(int gWnum);
}
