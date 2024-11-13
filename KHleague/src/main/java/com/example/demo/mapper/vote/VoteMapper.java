package com.example.demo.mapper.vote;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.vote.VoteDTO;

@Mapper
public interface VoteMapper {

	// 투표 정보 조회
	VoteDTO getVotes(); // 모든 투표 정보 (단일 투표 시스템을 가정)

	// 팀1 투표 수 업데이트
	void updateTeam1Vote(int votes);

	// 팀2 투표 수 업데이트
	void updateTeam2Vote(int votes);

	// 새로운 투표 정보 삽입 (기본 투표값은 0)
	void insertVote(VoteDTO voteDTO);
}
