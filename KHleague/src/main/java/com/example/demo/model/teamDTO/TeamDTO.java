package com.example.demo.model.teamDTO;

public class TeamDTO {
	private int teamnum;
	private String teamname;
	private int sportsnum;
	private String teamloggo;

	// Getter 및 Setter
	public int getTeamnum() {
		return teamnum;
	}

	public void setTeamnum(int teamnum) {
		this.teamnum = teamnum;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public int getSportsnum() {
		return sportsnum;
	}

	public void setSportsnum(int sportsnum) {
		this.sportsnum = sportsnum;
	}

	public String getTeamloggo() {
		return teamloggo;
	}

	public void setTeamloggo(String teamloggo) {
		this.teamloggo = teamloggo;
	}
}
