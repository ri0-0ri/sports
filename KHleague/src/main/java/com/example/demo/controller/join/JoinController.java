package com.example.demo.controller.join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.UserDTO.UserDTO;
import com.example.demo.service.user.UserService;

@Controller
@RequestMapping("/join")
public class JoinController {

    @Autowired
    private UserService userService;

    @GetMapping("join")
    public String showJoinPage() {
        return "join/join"; 
    }
    
    
    
    
    
//비밀번호 성공 했을 때 메인으로 이동해버리깅
    @PostMapping("join")
    public String registerUser(@ModelAttribute UserDTO user, Model model) {
        userService.registerUser(user);
        model.addAttribute("message", "회원가입이 성공적으로 완료되었습니다!");
        return "index"; 
    }
}
