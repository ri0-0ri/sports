package com.example.demo.service.GameScheduleService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.gWillBoard.GWillBoardMapper;
import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import com.example.demo.service.gWillBoard.GWillBoardService;

@Service
public class GameScheduleService {
	
	@Autowired
	private GWillBoardService gwservice;
	
	@Autowired
	private GWillBoardMapper gWillBoardMapper;

	// 일정이 끝난 경기를 g_end_board로 이동시키고, 랜덤 점수 추가
	@Scheduled(fixedRate = 60000) // 1분마다 체크
	public void moveToEndBoard() {
		// 현재 시간 구하기
		LocalDateTime currentTime = LocalDateTime.now();

		// g_will_board에서 모든 경기를 가져오기
		List<GWillBoardDTO> allGames = gWillBoardMapper.getgWillList();

		// 종료된 경기들 처리
		for (GWillBoardDTO game : allGames) {
			// 경기 시간이 지나면 종료된 경기로 이동
			if (game.getGwtime().isBefore(currentTime)) {

				// 랜덤으로 점수 생성 (0 ~ 10)
				Random random = new Random();
				int team1score = random.nextInt(11); // 0 ~ 10 사이의 랜덤 점수
				int team2score = random.nextInt(11); // 0 ~ 10 사이의 랜덤 점수

				game.setTeam1score(team1score);
				game.setTeam2score(team2score);
				game.setGEdate(currentTime); // 경기 종료 시간은 현재 시간으로 설정

				// 경기 종료 기록 추가
				gwservice.updateendgame(game);
			}
		}
	}
}
