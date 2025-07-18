package com.koreait.server.service;

import com.koreait.server.dto.UserDTO;
import com.koreait.server.jwt.JwtUtil;
import com.koreait.server.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserMapper mapper;
    private final JwtUtil jwtUtil;

    @Override
    public String login(String username, String password) {
        UserDTO user = mapper.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return jwtUtil.generateToken(user.getId(), user.getUsername()); // 수정됨
        }
        return null;
    }


    @Override
    public void register(UserDTO user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        mapper.save(user);
    }

    @Override
    public void update(String token, UserDTO updatedUser) {
        String jwt = token.replace("Bearer ", "");
        String username = jwtUtil.getUsernameFromToken(jwt);

        UserDTO original = mapper.findByUsername(username);
        if (original != null) {
            if (StringUtils.hasText(updatedUser.getPassword())) {
                String hashed = BCrypt.hashpw(updatedUser.getPassword(), BCrypt.gensalt());
                original.setPassword(hashed);
            }
            if (StringUtils.hasText(updatedUser.getName())) {
                original.setName(updatedUser.getName());
            }
            mapper.update(original);
        }
    }

    @Override
    public UserDTO getUserInfoFromToken(String token) {
        String jwt = token.replace("Bearer ", "");
        String username = jwtUtil.getUsernameFromToken(jwt);
        UserDTO user = mapper.findByUsername(username);
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }

    @Override
    public void logout(String token) {
    }
}
