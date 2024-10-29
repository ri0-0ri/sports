package com.example.demo.team;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team/*")
public class TeamController {
	
	@GetMapping("team_info")
	public void team_info() {
		
	}
	
	@GetMapping("soccer/info_fc_seoul")
	public void fc_seoul() {}
}
