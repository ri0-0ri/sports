package com.example.demo.controller.play_info;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;
import com.example.demo.model.dto.GEndBoard.GEndBoardDTO;
import com.example.demo.service.playInfo.PlayInfoService;

import java.util.List;

@Controller
@RequestMapping("/play_info/") // 이 부분은 그대로 두고
public class PlayInfoController {

	private final PlayInfoService playInfoService;

	// 생성자를 통한 주입 방식
	public PlayInfoController(PlayInfoService playInfoService) {
		this.playInfoService = playInfoService;
	}

	// 경기 정보 페이지
	@GetMapping("play_info")
	public String showPlayInfoPage(Model model) {
		// 경기 예정 데이터 가져오기
		List<GWillBoardDTO> upcomingGames = playInfoService.getUpcomingGames();
		// 경기 종료 데이터 가져오기
		List<GEndBoardDTO> endedGames = playInfoService.getEndedGames();

		model.addAttribute("upcomingGames", upcomingGames); // 예정된 경기 데이터
		model.addAttribute("endedGames", endedGames); // 종료된 경기 데이터
		return "play_info/play_info"; // 반환할 템플릿 경로
	}

	// 응원 페이지 처리
	@GetMapping("fighting") // /play_info/fighting 경로로 처리
	public String showFightingPage() {
		return "fighting/fighting"; // 'fighting/fighting.html' 템플릿 반환
	}


}
