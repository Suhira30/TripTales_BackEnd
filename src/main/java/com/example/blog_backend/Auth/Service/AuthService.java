package com.example.blog_backend.Auth.Service;

import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Auth.Entity.UserRole;
import com.example.blog_backend.Auth.Repository.UserRepository;
import com.example.blog_backend.Auth.Utils.AuthResponse;
import com.example.blog_backend.Auth.Utils.LoginRequest;
import com.example.blog_backend.Auth.Utils.RegisterRequest;
import com.example.blog_backend.Entity.Admin;
import com.example.blog_backend.Entity.Follower;
import com.example.blog_backend.Repository.AdminRepository;
import com.example.blog_backend.Repository.FollowerRepository;
import lombok.Builder;

import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Builder
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;
    private final AdminRepository adminRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, FollowerRepository followerRepository, AdminRepository adminRepository, JwtService jwtService, RefreshTokenService refreshTokenService, @Lazy AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.followerRepository = followerRepository;
        this.adminRepository = adminRepository;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
        this.adminRepository = adminRepository;
    }
    public AuthResponse registerFollower(RegisterRequest registerRequest){
        var user= new Follower();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setMobileNo(registerRequest.getMobileNo());
        user.setUserRole(UserRole.FOLLOWER);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
      
                User savedUser = followerRepository.save(user);
                var accessToken = jwtService.generateToken(savedUser);
                var refreshToken = refreshTokenService.createRefreshToken(savedUser.getEmail());

                return AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken.getRefreshToken())
                        .build();
            } 
  
    public AuthResponse registerAdmin(RegisterRequest registerRequest){
        String email= registerRequest.getEmail();
        Optional<User> admin=adminRepository.findByEmail(email);
        if(!(admin.isPresent())){
            try {
                var user = new Admin();
                user.setName(registerRequest.getName());
                user.setEmail(registerRequest.getEmail());
                user.setMobileNo(registerRequest.getMobileNo());
                user.setUserRole(UserRole.FOLLOWER);
                user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));


                User savedUser = adminRepository.save(user);
                var accessToken = jwtService.generateToken(savedUser);
                var refreshToken = refreshTokenService.createRefreshToken(savedUser.getEmail());

                return AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken.getRefreshToken())
                        .build();
            } catch (DataIntegrityViolationException exception) {
                throw new RuntimeException("Already have account ");
            }

        }else{
            throw new RuntimeException("Suspended Admin");
        }
    }

    public AuthResponse loginFollower(LoginRequest loginRequest){
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


    public AuthResponse loginAdmin(LoginRequest loginRequest){

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
