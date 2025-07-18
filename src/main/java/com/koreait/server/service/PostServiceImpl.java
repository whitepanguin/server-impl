package com.koreait.server.service;

import com.koreait.server.dto.PostDTO;
import com.koreait.server.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;

    public void writePost(PostDTO post) {
        postMapper.insertPost(post);
    }

    public Map<String, Object> getPostList(int page) {
        int limit = 10;
        int offset = (page - 1) * limit;

        List<PostDTO> posts = postMapper.getPosts(limit, offset);
        int total = postMapper.getTotalPostCount(); // 새로 추가된 메서드
        boolean hasNext = page * limit < total;

        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);
        result.put("hasNext", hasNext);
        result.put("total", total);
        return result;
    }


    public PostDTO getPostById(int id) {
        return postMapper.getPostById(id);
    }

    public boolean updatePost(PostDTO post, int userId) {
        PostDTO existing = postMapper.getPostById(post.getId());
        if (existing == null || existing.getWriterId() != userId) return false;
        return postMapper.updatePost(post) > 0;
    }

    public boolean deletePost(int id, int userId) {
        PostDTO existing = postMapper.getPostById(id);
        if (existing == null || existing.getWriterId() != userId) return false;
        return postMapper.deletePost(id) > 0;
    }


}
