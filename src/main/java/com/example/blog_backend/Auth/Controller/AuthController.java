package com.example.blog_backend.Auth.Controller;

import com.example.blog_backend.Auth.Entity.RefreshToken;
import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Auth.Service.AuthService;
import com.example.blog_backend.Auth.Service.JwtService;
import com.example.blog_backend.Auth.Service.RefreshTokenService;
import com.example.blog_backend.Auth.Utils.AuthResponse;
import com.example.blog_backend.Auth.Utils.LoginRequest;
import com.example.blog_backend.Auth.Utils.RefreshTokenRequest;
import com.example.blog_backend.Auth.Utils.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/")
@EnableWebSecurity
@EnableMethodSecurity
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, JwtService jwtService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }
    @PostMapping("register/follower")
    public ResponseEntity<AuthResponse> register (@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.registerFollower(registerRequest));
    }
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        RefreshToken refreshToken=refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
        User user =refreshToken.getUser();
        String accessToken =jwtService.generateToken(user);
        return ResponseEntity.ok(AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build());

    }
}
