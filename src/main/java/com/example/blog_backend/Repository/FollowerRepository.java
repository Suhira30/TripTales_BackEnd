package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower,Long> {
}
