package com.example.blog_backend.Controller;

import com.example.blog_backend.DTO.ReviewDTO;
import com.example.blog_backend.Entity.Review;
import com.example.blog_backend.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/v1/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping("/add")
    public ResponseEntity<ReviewDTO>addReview(@RequestBody ReviewDTO reviewDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok(reviewService.addReview(reviewDTO, currentPrincipalName));

    }
    //--------------------------------remove review Follower-----------------------------------------------
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeReview(@PathVariable("id") Long Id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        reviewService.removeReview(Id,currentPrincipalName);
    return ResponseEntity.noContent().build();
    }
    //--------------------------------All review -----------------------------------------------
    @GetMapping("/all")
    public ResponseEntity<List<ReviewDTO>> getAll(){
        return ResponseEntity.ok(reviewService.getAll());
    }
    //--------------------------------remove review ADMIN----------------------------------------
    @DeleteMapping("/removeAdmin/{id}")
    public ResponseEntity<Void> removeReviewByAdmin(@PathVariable("id") Long Id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        reviewService.removeReviewByAdmin(Id,currentPrincipalName);
        return ResponseEntity.noContent().build();
    }

}