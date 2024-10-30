package com.example.demo.mapper.user;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.UserDTO.UserDTO;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO user);

    @Select("SELECT COUNT(*) > 0 FROM user WHERE userid = #{userid}")
    boolean existsByUserId(String userid);
}
