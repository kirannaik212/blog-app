package com.blogApp.service;

import com.blogApp.dto.CommentDto;
import com.blogApp.dto.PostDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    CommentDto updateComment(CommentDto commentDto, Integer id);

    void deleteComment(Integer id);

    CommentDto getComment(Integer id);
}
