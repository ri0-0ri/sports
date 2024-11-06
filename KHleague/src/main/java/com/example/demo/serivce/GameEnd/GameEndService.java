package com.example.demo.serivce.GameEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GameEndService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 1분마다 실행
	@Scheduled(fixedRate = 60000) // 1분마다 실행
	public void checkGameEndAndUpdate() {
		String insertSql = "INSERT INTO g_end_board (gEdate, team1score, team2score, team1name, team2name) "
				+ "SELECT NOW(), FLOOR(RAND() * 11), FLOOR(RAND() * 11), " + "g.team1name, g.team2name "
				+ "FROM g_will_board g WHERE g.gwtime < NOW();";

		String deleteSql = "DELETE FROM g_will_board WHERE gwtime < NOW();";

		// 종료된 경기 데이터를 g_end_board에 추가
		jdbcTemplate.execute(insertSql);

		// 종료된 경기 데이터를 g_will_board에서 삭제
		jdbcTemplate.execute(deleteSql);
	}
}
