package com.example.blog_backend.Auth.Repository;

import com.example.blog_backend.Auth.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.OptionalInt;

public interface RefreshTokenRepository extends JpaRepository <RefreshToken,Integer> {
    Optional <RefreshToken> findByRefreshToken(String refreshToken);
}
