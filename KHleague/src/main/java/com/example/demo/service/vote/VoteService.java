package com.example.demo.service.vote;

import com.example.demo.mapper.vote.VoteMapper;
import com.example.demo.model.vote.VoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

	@Autowired
	private VoteMapper voteMapper;

	// 투표 누적 처리
	public boolean voteForTeam(String team) {
		VoteDTO voteDTO = voteMapper.getVotes(); // 현재 투표 상태 가져오기

		if (voteDTO == null) {
			// 만약 투표 정보가 없다면, 새로운 투표 정보를 생성하여 초기화
			voteDTO = new VoteDTO();
			voteDTO.setTeam1Vote(0);
			voteDTO.setTeam2Vote(0);
			voteMapper.insertVote(voteDTO);
		}

		if ("team1".equals(team)) {
			// 팀1 투표 수 증가
			voteMapper.updateTeam1Vote(voteDTO.getTeam1Vote() + 1);
		} else if ("team2".equals(team)) {
			// 팀2 투표 수 증가
			voteMapper.updateTeam2Vote(voteDTO.getTeam2Vote() + 1);
		} else {
			return false; // 잘못된 팀 이름일 경우
		}

		return true;
	}

	// 현재 투표 상태 조회
	public VoteDTO getVotes() {
		return voteMapper.getVotes();
	}
}
