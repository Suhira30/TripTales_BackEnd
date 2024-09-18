package com.example.blog_backend.DTO;

import com.example.blog_backend.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private String imageUrl;
    private String location;
    private String description;
}
