package com.example.blog_backend.DTO;
import com.example.blog_backend.Entity.Category;
import com.example.blog_backend.Entity.Continent;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
private Long postId;
    private String title;
    private String guide;
    private String experience;
    private Continent continent;
    private String location;
    private List<Category> category;
    private String imageUrl;
    private LocalDateTime postedOn;


    public PostDTO(String title, Continent continent, String imgUrl, String location, LocalDateTime postedOn) {
    }


}
