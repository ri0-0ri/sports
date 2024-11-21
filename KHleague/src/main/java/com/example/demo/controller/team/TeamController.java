package com.example.demo.controller.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.teamDTO.PlayerDTO;
import com.example.demo.service.team.TeamService;

@Controller
@RequestMapping("/team/*")
public class TeamController {
	
	@Autowired
	TeamService tservice;

	@GetMapping("team_info")
	public void team_info() {

	}

	@GetMapping("soccer/info_fc_seoul")
	public void fc_seoul(Model model) {
		int teamnum = 1;
		List<PlayerDTO> playerlist = tservice.getplayers(teamnum);
		System.out.println(playerlist);
		model.addAttribute("playerlist", playerlist);
	}

}