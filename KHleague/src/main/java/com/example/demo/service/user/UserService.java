package com.example.demo.service.user;

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


}
