package com.example.demo.controller.fighting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fighting/")
public class FightingController {

    @GetMapping("fighting") 
    public String showFightingPage() {
        return "fighting/fighting"; 
    }
}
