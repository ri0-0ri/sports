package com.example.demo.controller.join;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.UserDTO.UserDTO;
import com.example.demo.model.moneyDTO.MoneyDTO;
import com.example.demo.service.money.MoneyService;
import com.example.demo.service.user.UserService;

@Controller
@RequestMapping("/join")
public class JoinController {

	@Autowired
	private UserService userService;
	
	@Autowired
	MoneyService mservice;

	@GetMapping("join")
	public String showJoinPage() {
		return "join/join";
	}

//비밀번호 성공 했을 때 메인으로 이동해버리깅
	@PostMapping("join")
	public String registerUser(@ModelAttribute UserDTO user, Model model) {
		userService.registerUser(user);
		
		// 가입 적립금 5000원 추가
		MoneyDTO money = new MoneyDTO();
		money.setMoneytype("적립금");
		money.setUserid(user.getUserid());
		money.setMoneyname("가입 축하 적립금");
		money.setChange_money("+5000");
		mservice.putmoney(money);
		
		model.addAttribute("message", "회원가입이 성공적으로 완료되었습니다!");
		return "index";
	}

	@GetMapping("checkUserId")
	@ResponseBody
	public Map<String, Boolean> checkUserId(@RequestParam String userid) {
		boolean exists = userService.checkUserIdExists(userid);
		Map<String, Boolean> response = new HashMap<>();
		response.put("exists", exists);
		return response;
	}

}
