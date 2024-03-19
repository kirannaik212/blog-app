package com.blogApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotBlank(message = "Title should not be Empty")
    @Size(min=4 , max = 200)
    private String categoryTitle;

    @NotBlank(message = "Description should not be empty")
    @Size(min=4 , max = 400)
    private String categoryDescription;
}
