package com.koreait.server.mapper;

import com.koreait.server.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    void insertPost(PostDTO post);
    List<PostDTO> getPosts(@Param("limit") int limit, @Param("offset") int offset);
    PostDTO getPostById(@Param("id") int id);
    int updatePost(@Param("post") PostDTO post);
    int deletePost(@Param("id") int id);
}
