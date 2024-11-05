package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.UserDTO.UserDTO;
import com.example.demo.service.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login_page/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map<String, String> loginData, HttpSession session) {
        String userid = loginData.get("userid");
        String password = loginData.get("password");

        Map<String, Object> response = new HashMap<>();

        // 관리자 계정 확인
        if ("admin".equals(userid) && "12345678".equals(password)) {
            session.setAttribute("loginUser", userid);
            response.put("success", true);
            response.put("redirect", "/admin/admin_user"); // 관리자 페이지로 리다이렉트
            return response;
        }

        UserDTO user = userService.findUserById(userid);

        if (user != null && user.getUserpw().equals(password)) {
            session.setAttribute("loginUser", user.getUserid());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("useraddr", user.getUseraddr());
            session.setAttribute("userphone", user.getUserphone());
            response.put("success", true);
            response.put("redirect", "/"); // 로그인 성공 시 일반 사용자 페이지로 리다이렉트
        } else {
            response.put("success", false);
            response.put("error", "아이디 또는 비밀번호가 잘못되었습니다.");
        }

        return response;
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate(); // 세션 무효화
        return "redirect:/"; // 메인 페이지로 리디렉션
    }

    @PostMapping("/updateUserInfo")
    @ResponseBody
    public ResponseEntity<Void> updateUserInfo(@RequestParam String field, @RequestParam String value,
            HttpSession session) {
        String userid = (String) session.getAttribute("loginUser");
        userService.updateUserField(userid, field, value); // 필드와 값으로 업데이트

        return ResponseEntity.ok().build();
    }
    @GetMapping("/admin/users")
    @ResponseBody
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers(); // 모든 유저를 반환하는 서비스 메서드 호출
    }
    @DeleteMapping("/admin/deleteUser")
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@RequestParam String userid) {
        userService.deleteUser(userid); // 유저 삭제를 위한 서비스 메서드 호출
        return ResponseEntity.ok().build();
    }

}
