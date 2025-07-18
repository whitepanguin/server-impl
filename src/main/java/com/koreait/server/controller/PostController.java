package com.koreait.server.controller;

import com.koreait.server.dto.PostDTO;
import com.koreait.server.jwt.JwtUtil;
import com.koreait.server.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final JwtUtil jwtUtil;

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody PostDTO post,
                                   HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("인증 실패: 토큰 없음");
        }

        token = token.substring(7);
        if (!jwtUtil.isTokenValid(token)) {
            return ResponseEntity.status(401).body("인증 실패: 토큰 유효하지 않음");
        }

        int userId = jwtUtil.getUserIdFromToken(token); // 사용자 ID 추출
        post.setWriterId(userId); // 작성자 ID 설정
        postService.writePost(post);

        return ResponseEntity.ok("게시글 작성 완료");
    }

    @GetMapping("/list")
    public ResponseEntity<?> getPostList(@RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(postService.getPostList(page));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPostDetail(@PathVariable int id) {
        PostDTO post = postService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.status(404).body("게시글을 찾을 수 없습니다.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable int id,
                                        @RequestBody PostDTO post,
                                        HttpServletRequest request) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        post.setId(id);
        boolean result = postService.updatePost(post, userId);
        if (result) {
            return ResponseEntity.ok("수정 완료");
        } else {
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id,
                                        HttpServletRequest request) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        boolean result = postService.deletePost(id, userId);
        if (result) {
            return ResponseEntity.ok("삭제 완료");
        } else {
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }
    }


}
