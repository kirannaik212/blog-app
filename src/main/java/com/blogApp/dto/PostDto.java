package com.blogApp.dto;

import com.blogApp.entity.Category;
import com.blogApp.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @NotBlank(message = "post title should not be blank")
    @Size(min = 5, max = 50)
    private String title;

    @NotBlank(message = "post title should not be blank")
    @Size(min = 5, max = 100)
    private String content;

    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;

    private List<CommentDto> comments=new ArrayList<>();

}
