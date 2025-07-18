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
    }

