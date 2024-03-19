package com.blogApp.controller;

import com.blogApp.dto.CommentDto;
import com.blogApp.payloads.ApiResponse;
import com.blogApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDto> createPost(@RequestBody CommentDto commentDto,@PathVariable Integer postId) {
        CommentDto comment = commentService.createComment(commentDto,postId);
        return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void createPost(@PathVariable Integer id) {
        commentService.deleteComment(id);
       // return new ResponseEntity<ApiResponse>()
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Integer id){
        CommentDto comment = commentService.getComment(id);
        return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
    }


}
