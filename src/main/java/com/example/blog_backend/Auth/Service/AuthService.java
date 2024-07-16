package com.example.blog_backend.Auth.Service;

import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Auth.Repository.UserRepository;
import com.example.blog_backend.Auth.Utils.AuthResponse;
import com.example.blog_backend.Auth.Utils.LoginRequest;
import com.example.blog_backend.Auth.Utils.RegisterRequest;
import com.example.blog_backend.Entity.Follower;
import com.example.blog_backend.Repository.FollowerRepository;
import lombok.Builder;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, FollowerRepository followerRepository, JwtService jwtService, RefreshTokenService refreshTokenService, @Lazy AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.followerRepository = followerRepository;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
    }
    public AuthResponse registerFollower(RegisterRequest registerRequest){
        var user= new Follower();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setMobileNo(registerRequest.getMobileNo());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        User savedUser= followerRepository.save(user);
        var accessToken=jwtService.generateToken(savedUser);
        var refreshToken=refreshTokenService.createRefreshToken(savedUser.getEmail());

    return AuthResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken.getRefreshToken())
            .build();
    }
    public AuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new UsernameNotFoundException("User not found "));
        var accessToken=jwtService.generateToken(user);
        var refreshToken=refreshTokenService.createRefreshToken(loginRequest.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }
}
