package com.example.demo.modal.gWillBoardDTO;

import java.time.LocalDateTime;

public class GWillBoardDTO {
	private int gWnum; // 기본 키
	private LocalDateTime gWdate; // 일정 날짜
	private String team1name; // 팀 1 이름
	private String team2name; // 팀 2 이름
	private LocalDateTime gwtime; // 경기 시간

	// Getter 및 Setter
	public int getGWnum() {
		return gWnum;
	}

	public void setGWnum(int gWnum) {
		this.gWnum = gWnum;
	}

	public LocalDateTime getGWdate() {
		return gWdate;
	}

	public void setGWdate(LocalDateTime gWdate) {
		this.gWdate = gWdate;
	}

	public String getTeam1name() {
		return team1name;
	}

	public void setTeam1name(String team1name) {
		this.team1name = team1name;
	}

	public String getTeam2name() {
		return team2name;
	}

	public void setTeam2name(String team2name) {
		this.team2name = team2name;
	}

	public LocalDateTime getGwtime() {
		return gwtime;
	}

	public void setGwtime(LocalDateTime gwtime) {
		this.gwtime = gwtime;
	}
}
