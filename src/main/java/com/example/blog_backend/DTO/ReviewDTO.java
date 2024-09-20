package com.example.blog_backend.DTO;

import com.example.blog_backend.Entity.Follower;
import com.example.blog_backend.Entity.Post;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private Long reviewId;
    private String description;
    private LocalDateTime postedAt;
    private Long reviewTo;
    private String reviewBy;



}
