package com.example.blog_backend.Auth.Controller;

import com.example.blog_backend.Auth.Service.AuthService;
import com.example.blog_backend.Auth.Utils.AuthResponse;
import com.example.blog_backend.Auth.Utils.LoginRequest;
import com.example.blog_backend.Auth.Utils.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("register/follower")
    public ResponseEntity<AuthResponse> register (@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.registerFollower(registerRequest));
    }
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
