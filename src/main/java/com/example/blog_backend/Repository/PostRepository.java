package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("SELECT p FROM Post p WHERE p.postId = (SELECT MAX(p2.postId) FROM Post p2)")
    Post getLastPost();

    Post findTopByOrderByPostedOnDesc();

}
