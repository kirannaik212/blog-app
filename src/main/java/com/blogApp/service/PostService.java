package com.blogApp.service;

import com.blogApp.dto.PostDto;
import com.blogApp.dto.UserDto;
import com.blogApp.entity.Category;
import com.blogApp.entity.Post;
import com.blogApp.entity.PostResponse;
import com.blogApp.entity.User;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer catId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostDto getPostById(Integer postId);

    PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> searchPost(String title);
}
