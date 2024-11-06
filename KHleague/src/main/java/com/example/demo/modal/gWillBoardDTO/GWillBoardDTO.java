package com.example.demo.modal.gWillBoardDTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GWillBoardDTO {
	private int gWnum; // 기본 키
	private LocalDateTime gWdate; // 일정 날짜
	private String team1name; // 팀 1 이름
	private String team2name; // 팀 2 이름
	private LocalDateTime gwtime; // 경기 시간

}
