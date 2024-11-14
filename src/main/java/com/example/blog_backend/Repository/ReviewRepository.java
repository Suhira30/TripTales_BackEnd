package com.example.blog_backend.Repository;

import com.example.blog_backend.DTO.ReviewDTO;
import com.example.blog_backend.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByOrderByReviewIdDesc();

    @Query("select r from Review r where r.reviewTo.postId = :postId")
    List<Review> findByPostId(@Param("postId") Long postId);
}
