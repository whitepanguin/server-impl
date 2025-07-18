# 📝 JWT 기반 게시판 REST API 프로젝트

Spring Boot, MyBatis, JWT 인증을 활용한 간단한 게시판 REST API 프로젝트입니다.  
로그인한 사용자만 게시글을 작성, 수정, 삭제할 수 있으며,  
비회원도 게시글 목록과 상세 조회는 가능합니다.

---

## 📌 주요 기능

### ✅ 사용자 기능
- 회원가입 (`/api/user/register`)
- 로그인 & JWT 발급 (`/api/user/login`)
- 사용자 정보 조회 (`/api/user/info`)
- 회원정보 수정 (`/api/user/update`)
- 로그아웃 (`/api/user/logout`)

### ✅ 게시판 기능
- 게시글 작성 (로그인 필요)
- 게시글 목록 조회 (페이징, 최신순)
- 게시글 상세 조회
- 게시글 수정 (작성자 본인만)
- 게시글 삭제 (작성자 본인만)

---

## 🧱 기술 스택

| 항목 | 사용 기술 |
|------|-----------|
| 백엔드 | Spring Boot 3.x |
| ORM | MyBatis |
| 인증 | JWT (jjwt) |
| DB | MySQL |
| 뷰 | HTML + JS (Fetch API 기반) |
| 빌드 도구 | Gradle |

---

## 🗂 프로젝트 구조

src/
├── main/
│ ├── java/com/koreait/server/
│ │ ├── config/ # 접근 허가
│ │ ├── controller/ # REST + View Controller
│ │ ├── service/ # 비즈니스 로직
│ │ ├── mapper/ # MyBatis 인터페이스
│ │ ├── dto/ # DTO 클래스
│ │ └── jwt/ # JWT 유틸리티
│ └── resources/
│ ├── mapper/ # MyBatis XML
│ ├── templates/ # HTML 파일
│ └── application.yml

---

## ⚙️ 실행 방법

### 1. 프로젝트 클론 및 빌드

```bash
git clone https://github.com/whitepanguin/server-impl.git
cd jwt-board
./gradlew build

MySQL DB
CREATE DATABASE boarddb DEFAULT CHARACTER SET utf8mb4;

USE boarddb;

-- 회원 테이블
CREATE TABLE user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(50),
  ph VARCHAR(20)
);

-- 게시글 테이블
CREATE TABLE post (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  writer_id INT NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

application.yml
spring.application.name=server

spring.datasource.url=jdbc:mysql://localhost:3306/spring?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.type-aliases-package=com.koreait.server.dto

jwt.secret=sdfkljnwikelfotgshljesjkflhoglsnrfj
jwt.expiration=3600000

----

서버는 8080
http://localhost:8080
---
주요화면
| 경로                          | 설명                |
| --------------------------- | ----------------- |
| `/login`                    | 로그인 페이지           |
| `/register`                 | 회원가입 페이지          |
| `/post-list.html`           | 게시글 목록            |
| `/post-write.html`          | 게시글 작성            |
| `/post-detail.html?id={id}` | 게시글 상세 (수정/삭제 포함) |

