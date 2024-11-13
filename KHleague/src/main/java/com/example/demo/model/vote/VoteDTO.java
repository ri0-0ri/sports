package com.example.demo.model.vote;

import lombok.Data;

@Data
public class VoteDTO {
	private int voteId; // 고유 투표 ID
	private int team1Vote; // 팀1의 누적 투표 수
	private int team2Vote; // 팀2의 누적 투표 수
}
