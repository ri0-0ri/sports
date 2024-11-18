package com.example.demo.mapper.user;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
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

    @Select("SELECT * FROM user")
    List<UserDTO> findAllUsers(); // 모든 유저를 선택하는 메서드
    @Delete("DELETE FROM user WHERE userid = #{userid}")
    void deleteUser(String userid); // 유저 삭제 메서드

	void putpoint(int point, String userid);

	void updateUserReward(int newReward, String userid);

	void updateUser(UserDTO user);

	void putwinevent(String neweventstr, String winner);
    
}
