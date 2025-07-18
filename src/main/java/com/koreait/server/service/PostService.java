package com.koreait.server.service;

import com.koreait.server.dto.PostDTO;

import java.util.List;
import java.util.Map;

public interface PostService {
    public void writePost(PostDTO post);
    public Map<String, Object> getPostList(int page);
    public PostDTO getPostById(int id);
    public boolean updatePost(PostDTO post, int userId);
    public boolean deletePost(int id, int userId);
}
