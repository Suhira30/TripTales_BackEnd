package com.example.blog_backend.Service;

import com.example.blog_backend.Auth.Entity.UserRole;
import com.example.blog_backend.Repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowerService {
    @Autowired
    public FollowerRepository followerRepository;
    public int getNoOfFollowers() {
    return (int) followerRepository.countByUserRole(UserRole.FOLLOWER);

    }
}
