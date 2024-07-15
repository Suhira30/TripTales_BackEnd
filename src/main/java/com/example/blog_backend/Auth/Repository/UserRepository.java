package com.example.blog_backend.Auth.Repository;


import com.example.blog_backend.Auth.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String username);
}
