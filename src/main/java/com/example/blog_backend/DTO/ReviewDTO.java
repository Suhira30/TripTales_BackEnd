package com.example.blog_backend.DTO;

import com.example.blog_backend.Entity.Follower;
import com.example.blog_backend.Entity.Post;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long reviewId;
    private String description;
    private LocalDateTime postedAt;
    private Post reviewTo;
    private Follower reviewBy;

    public ReviewDTO(Long reviewId, String description, LocalDateTime postedAt, Long postId, String email) {
    }
}
