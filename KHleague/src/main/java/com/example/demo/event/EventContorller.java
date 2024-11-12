package com.example.demo.event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("event/*")
public class EventContorller {
	
	@GetMapping("notice")
	public void notice() {
		
	}
}
