package com.koreait.server.mapper;

import com.koreait.server.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserDTO findByUsername(@Param("username") String username);
    void save(UserDTO user);
    void update(UserDTO user);
}
