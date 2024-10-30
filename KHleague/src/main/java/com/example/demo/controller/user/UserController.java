package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.UserDTO.UserDTO;
import com.example.demo.service.user.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login_page/login")
	@ResponseBody
	public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
		String userid = loginData.get("userid");
		String password = loginData.get("password");

		Map<String, Object> response = new HashMap<>();
		UserDTO user = userService.findUserById(userid);

		if (user != null && user.getUserpw().equals(password)) {
			// 로그인 성공 시 세션에 사용자 정보 저장 (선택적)
			// session.setAttribute("username", user.getUsername()); // 세션에 사용자 이름 저장

			response.put("success", true);
			response.put("redirect", "/"); // 로그인 성공 시 이동할 경로
		} else {
			response.put("success", false);
			response.put("error", "아이디 또는 비밀번호가 잘못되었습니다.");
		}

		return response;
	}
}
