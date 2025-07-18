package com.koreait.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // templates/index.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // templates/login.html
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // templates/register.html
    }

    @GetMapping("/mypage")
    public String myPage() {
        return "mypage"; // templates/mypage.html
    }

    // 게시글 목록 페이지
    @GetMapping("/posts")
    public String postListPage() {
        return "post_list"; // templates/post_list.html
    }

    // 게시글 작성 페이지
    @GetMapping("/posts/write")
    public String postWritePage() {
        return "post_write"; // templates/post_write.html
    }

    // 게시글 상세 페이지
    @GetMapping("/posts/{id}")
    public String postDetailPage() {
        return "post_detail"; // templates/post_detail.html
    }

    // 게시글 수정 페이지
    @GetMapping("/posts/edit/{id}")
    public String postEditPage() {
        return "post_edit"; // templates/post_edit.html
    }
}
