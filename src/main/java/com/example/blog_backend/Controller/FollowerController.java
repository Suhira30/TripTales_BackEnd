package com.example.blog_backend.Controller;

import com.example.blog_backend.Service.FollowerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/v1/follower")
public class FollowerController {
    @Autowired
    private FollowerService followerService;
    @GetMapping("/noOfFollower")
    public ResponseEntity<Long> getNoOfFollowers (){
        long tot=followerService.getNoOfFollowers();
        return ResponseEntity.ok(tot);
    }
}
