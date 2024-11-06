package com.example.demo.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.user.UserMapper;
import com.example.demo.model.UserDTO.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public void registerUser(UserDTO user) {
		userMapper.insertUser(user);
	}

	public boolean checkUserIdExists(String userid) {
		return userMapper.existsByUserId(userid);
	}

	public UserDTO findUserById(String userid) {
		return userMapper.findUserById(userid);
	}

	public void updateUserField(String userid, String field, String value) {
		switch (field) {
		case "username":
			userMapper.updateUsername(userid, value);
			break;
		case "userphone":
			userMapper.updateUserphone(userid, value);
			break;
		case "useraddr":
			userMapper.updateUseraddr(userid, value);
			break;
		case "userid":
			userMapper.updateUserid(userid, value);
			break;
		}
	}

	public List<UserDTO> getAllUsers() {
		return userMapper.findAllUsers(); // 모든 유저를 반환하는 매퍼 메서드 호출
	}

	public void deleteUser(String userid) {
		userMapper.deleteUser(userid); // 유저 삭제를 위한 매퍼 메서드 호출
	}

	public void putpoint(int point, String userid) {
		userMapper.putpoint(point, userid);
	}

	
	 public void updateUserReward(int newReward, String userid) {
	 userMapper.updateUserReward(newReward, userid);
	 }

}
