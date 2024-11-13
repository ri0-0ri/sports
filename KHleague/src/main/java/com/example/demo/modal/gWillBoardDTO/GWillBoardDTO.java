package com.example.demo.modal.gWillBoardDTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GWillBoardDTO {
	private int gWnum; // 기본 키
	private LocalDateTime gWdate; // 일정 날짜
	private LocalDateTime gEdate;
	private String team1name; // 팀 1 이름
	private String team2name; // 팀 2 이름
	private Integer team1score; // 팀 1 점수
	private Integer team2score; // 팀 2 점수
	private LocalDateTime gwtime; // 경기 시간

}
