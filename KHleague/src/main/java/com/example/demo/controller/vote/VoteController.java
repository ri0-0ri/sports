package com.example.demo.controller.vote;

import com.example.demo.model.vote.VoteDTO;
import com.example.demo.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {

	@Autowired
	private VoteService voteService;

	// 투표 처리
	@PostMapping("/voteForTeam")
	public String voteForTeam(@RequestParam String team) {
		boolean success = voteService.voteForTeam(team); // 투표 처리
		if (success) {
			return "투표가 완료되었습니다!";
		} else {
			return "투표에 실패했습니다.";
		}
	}

	// 현재 투표 상태 조회
	@GetMapping("/getVotes")
	public VoteDTO getVotes() {
		return voteService.getVotes(); // 현재 투표 상태 반환
	}
}
