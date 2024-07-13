package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
