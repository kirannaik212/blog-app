package com.blogApp.entity;

import com.blogApp.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> list;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalElements;
    private Integer totalPages;
    private Boolean lastPage;
}
