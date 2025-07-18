package com.koreait.server.service;


import com.koreait.server.dto.UserDTO;

public interface UserService {
    public String login(String username, String password);
    public void register(UserDTO user);
    public void update(String token, UserDTO updatedUser);
    public void logout(String token); // optional
    public UserDTO getUserInfoFromToken(String token);
}
