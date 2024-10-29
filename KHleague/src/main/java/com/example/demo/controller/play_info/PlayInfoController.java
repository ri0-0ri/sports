package com.example.demo.controller.play_info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/play_info/")
public class PlayInfoController {

	@GetMapping("play_info")
	public String ShowPlayInfoPage() {
		return "play_info/play_info";
	}
}
