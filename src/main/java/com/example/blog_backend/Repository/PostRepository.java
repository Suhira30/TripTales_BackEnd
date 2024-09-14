package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
//    List<Post> findTop10ByOrderByIdDesc();
}
