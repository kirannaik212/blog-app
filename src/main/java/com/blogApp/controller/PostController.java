package com.blogApp.controller;

import com.blogApp.dto.PostDto;
import com.blogApp.entity.Category;
import com.blogApp.entity.PostResponse;
import com.blogApp.entity.User;
import com.blogApp.payloads.ApiResponse;
import com.blogApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{catId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer catId) {
        PostDto post = postService.createPost(postDto, userId, catId);
        return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        return new ResponseEntity<PostDto>(postService.updatePost(postDto, postId), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        return new ResponseEntity<PostDto>(postService.getPostById(postId), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize,
                                                   @RequestParam(value= "sortBy", required = false, defaultValue = "postId") String sortBy,
                                                   @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {
        PostResponse postResponse = postService.getAllPost(pageNo, pageSize, sortBy,sortDir);

        return ResponseEntity.ok(postResponse);
    }

    @DeleteMapping
    public void deletePostById(@PathVariable Integer postId) {
        postService.deletePost(postId);
        //return new ResponseEntity<ApiResponse>(postService.deletePost(postId),HttpStatus.OK));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> postByUser = postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postByCategory = postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postByCategory, HttpStatus.OK);
    }


    @GetMapping("/search/{title}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String title){
        List<PostDto> postDtoList = postService.searchPost(title);
        return ResponseEntity.ok(postDtoList);
    }


}
