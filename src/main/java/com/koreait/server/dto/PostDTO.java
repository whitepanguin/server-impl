package com.koreait.server.dto;

import lombok.Data;

@Data
public class PostDTO {
    private int id;
    private String title;
    private String content;
    private int writerId;
    private String createdAt;
}
