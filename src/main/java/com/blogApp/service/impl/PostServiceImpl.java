package com.blogApp.service.impl;

import com.blogApp.dto.PostDto;
import com.blogApp.entity.Category;
import com.blogApp.entity.Post;
import com.blogApp.entity.PostResponse;
import com.blogApp.entity.User;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.repositories.CategoryRepository;
import com.blogApp.repositories.PostRepository;
import com.blogApp.repositories.UserRepository;
import com.blogApp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        Category category = categoryRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("category", "id", catId));

        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("https://c8.alamy.com/comp/2AGB2F6/five-stages-of-category-management-2AGB2F6.jpg");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "is", postId));
        post.setContent(postDto.getContent());
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", " id", postId));
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        return modelMapper.map(post, PostDto.class);

    }

    @Override
    public PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }
        PageRequest request = PageRequest.of(pageNo, pageSize, sort);


        Page<Post> posts = postRepository.findAll(request);
        List<Post> postList = posts.getContent();
        List<PostDto> listOfPosts = postList.stream().map((post) -> modelMapper.map(post, PostDto.class)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setList(listOfPosts);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getNumberOfElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        List<Post> postByUser = postRepository.findByUser(user);
        return postByUser.stream().map((post) -> modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));

        List<Post> postByCategory = postRepository.findByCategory(category);
        return postByCategory.stream().map((post) -> modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> searchPost(String title) {
        List<Post> postList = postRepository.findByTitleContaining(title);
        return postList.stream().map((post) -> modelMapper.map(post, PostDto.class)).toList();
    }
}
