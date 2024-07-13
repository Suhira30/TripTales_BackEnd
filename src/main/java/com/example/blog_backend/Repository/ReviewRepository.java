package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
