package com.example.demo.controller.join;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join")
public class JoinController {

	 @GetMapping("join") 
	    public String showJoinPage() {
	        return "join/join"; 
	    }
}
