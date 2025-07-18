package com.koreait.server.service;

import com.koreait.server.dto.PostDTO;

import java.util.List;

public interface PostService {
    public void writePost(PostDTO post);
    public List<PostDTO> getPostList(int page);
    public PostDTO getPostById(int id);
    public boolean updatePost(PostDTO post, int userId);
    public boolean deletePost(int id, int userId);
}
