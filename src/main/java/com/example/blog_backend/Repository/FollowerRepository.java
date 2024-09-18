package com.example.blog_backend.Repository;


import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Auth.Entity.UserRole;

import com.example.blog_backend.Auth.Repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface FollowerRepository extends UserRepository {
    Optional<User> findByEmail(String email);

    long countByUserRole(UserRole userRole);

}
