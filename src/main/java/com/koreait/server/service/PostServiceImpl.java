package com.koreait.server.service;

import com.koreait.server.dto.PostDTO;
import com.koreait.server.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;

    public void writePost(PostDTO post) {
        postMapper.insertPost(post);
    }

    public List<PostDTO> getPostList(int page) {
        int limit = 10;
        int offset = (page - 1) * limit;
        return postMapper.getPosts(limit, offset);
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
