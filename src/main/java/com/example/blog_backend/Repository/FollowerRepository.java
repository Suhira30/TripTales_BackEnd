package com.example.blog_backend.Repository;

import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Auth.Repository.UserRepository;
import com.example.blog_backend.Entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<Follower,String> {
    Optional<Follower> findByEmail(String email);
}
