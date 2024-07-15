package com.example.blog_backend.Auth.Service;

import com.example.blog_backend.Auth.Entity.RefreshToken;
import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Auth.Repository.RefreshTokenRepository;
import com.example.blog_backend.Auth.Repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
private final RefreshTokenRepository refreshTokenRepository;
private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(String username){
        User user=userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user Not found "+ username));
        RefreshToken refreshToken=user.getRefreshToken();
        if(refreshToken==null){
          long  refreshTokenValidity=5*60*60*10000; //refresh token validity
            refreshToken=RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(refreshTokenValidity))
                    .user(user)
                    .build();
            refreshTokenRepository.save(refreshToken);
        }
        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken){
       RefreshToken refToken=refreshTokenRepository.findByRefreshToken(refreshToken)
               .orElseThrow(()->new RuntimeException("Refresh Token not found"));
       if(refToken.getExpirationTime().compareTo(Instant.now())<0){
           refreshTokenRepository.delete(refToken);
           throw new RuntimeException("Refresh Token expired");
       }
       return refToken;

    }
}
