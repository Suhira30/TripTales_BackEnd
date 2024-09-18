package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByOrderByReviewIdDesc();
}
