package com.example.blog_backend.DTO;

import com.example.blog_backend.Entity.Category;
import com.example.blog_backend.Entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private String title;
    private String description;
    private String location;
    private List<Category> category;
    private List<Image> images;
}
