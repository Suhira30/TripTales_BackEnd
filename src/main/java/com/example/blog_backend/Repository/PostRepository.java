package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Category;
import com.example.blog_backend.Entity.Continent;
import com.example.blog_backend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("SELECT p FROM Post p WHERE p.postId = (SELECT MAX(p2.postId) FROM Post p2)")
    Post getLastPost();

    @Query("SELECT p FROM Post p WHERE :category IN ELEMENTS(p.category) order by p.postId DESC" )
    List<Post> getPostByCategory(@Param("category") Category category);

    @Query("SELECT p FROM Post p WHERE p.continent = :continent order by p.postId DESC" )
    List<Post> getPostByArea(@Param("continent") Continent continent);
    @Query("SELECT p FROM Post p LEFT JOIN p.review r GROUP BY p ORDER BY COUNT(r) DESC")
    List<Post> getPoppularPost();


}
