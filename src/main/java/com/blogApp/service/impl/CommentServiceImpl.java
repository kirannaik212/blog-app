package com.blogApp.service.impl;

import com.blogApp.dto.CommentDto;
import com.blogApp.entity.Comment;
import com.blogApp.entity.Post;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.repositories.CommentRepository;
import com.blogApp.repositories.PostRepository;
import com.blogApp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto,Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer id) {
        return null;
    }

    @Override
    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", "id", id));
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto getComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", "id", id));
        return modelMapper.map(comment,CommentDto.class);
    }
}
