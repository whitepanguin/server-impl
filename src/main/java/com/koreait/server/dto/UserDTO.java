package com.koreait.server.dto;


import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String name;
    private String ph;
}
