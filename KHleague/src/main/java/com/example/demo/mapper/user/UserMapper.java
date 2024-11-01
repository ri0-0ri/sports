package com.example.demo.mapper.user;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.UserDTO.UserDTO;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO user);

    @Select("SELECT COUNT(*) > 0 FROM user WHERE userid = #{userid}")
    boolean existsByUserId(String userid);
    
    UserDTO findUserById(String userid);
    void updateUsername(@Param("userid") String userid, @Param("value") String username);
    void updateUserphone(@Param("userid") String userid, @Param("value") String userphone);
    void updateUseraddr(@Param("userid") String userid, @Param("value") String useraddr);
    void updateUserid(@Param("userid") String userid, @Param("value") String newUserid);


}
