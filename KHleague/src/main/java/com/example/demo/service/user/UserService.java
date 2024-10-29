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
}
