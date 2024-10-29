package com.example.demo.mapper.user;


import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.UserDTO.UserDTO;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO user);
}
