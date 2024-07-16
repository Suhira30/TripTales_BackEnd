package com.example.blog_backend.Auth.Config;

import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Auth.Repository.UserRepository;
import com.example.blog_backend.Entity.Admin;
import com.example.blog_backend.Entity.Follower;
import com.example.blog_backend.Repository.AdminRepository;
import com.example.blog_backend.Repository.FollowerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class ApplicationConfig {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final FollowerRepository followerRepository;

    public ApplicationConfig(UserRepository userRepository, AdminRepository adminRepository, FollowerRepository followerRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.followerRepository = followerRepository;
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return username ->userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user not found with email :"+username));
//    }
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            Optional <Admin> admin=adminRepository.findByEmail(username);
            if(admin.isPresent()){
                return admin.get();
            }
            Optional <Follower> follower=followerRepository.findByEmail(username);
            if(follower.isPresent()){
                return follower.get();
            }
            throw new UsernameNotFoundException("user not found with email :"+username);
        };
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean

    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
    return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
