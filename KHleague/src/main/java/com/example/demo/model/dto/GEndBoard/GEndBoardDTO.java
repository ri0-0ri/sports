package com.example.demo.model.dto.GEndBoard;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GEndBoardDTO {
	private int gEnum; // 경기 종료 번호 (기본키)
	private LocalDateTime gEdate; // 경기 종료 시간
	private int team1score; // 팀 1 점수
	private int team2score; // 팀 2 점수
	private String team1name; // 팀 1 이름
	private String team2name; // 팀 2 이름
}
