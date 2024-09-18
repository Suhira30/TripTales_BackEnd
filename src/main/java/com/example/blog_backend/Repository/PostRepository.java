package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

//    List<Post> findTop10ByOrderByIdDesc();
    Post findTopByOrderByPostIdDesc();

    Post findTopByOrderByPostIdAsc();

    Post findTopByOrderByPostedOnDesc();

}
