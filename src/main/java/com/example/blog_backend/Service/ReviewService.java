package com.example.blog_backend.Service;

import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Auth.Entity.UserRole;
import com.example.blog_backend.Auth.Repository.UserRepository;
import com.example.blog_backend.DTO.ReviewDTO;
import com.example.blog_backend.Entity.Follower;
import com.example.blog_backend.Entity.Post;
import com.example.blog_backend.Entity.Review;
import com.example.blog_backend.Repository.FollowerRepository;
import com.example.blog_backend.Repository.PostRepository;
import com.example.blog_backend.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    //--------------------------------add review ---------------------------------------
    public ReviewDTO addReview(ReviewDTO reviewDTO, String currentPrincipalName) {
    Review newReview =new Review();
//        newReview.setReviewTo(reviewDTO.getReviewTo());
        newReview.setDescription(reviewDTO.getDescription());
        newReview.setPostedAt(LocalDateTime.now());
        User follower=followerRepository.findById(currentPrincipalName)
                                            .orElseThrow(()-> new RuntimeException("Follower not avilable"));
        if(follower.getUserRole()== UserRole.FOLLOWER){
            newReview.setReviewBy((Follower) follower);
        }
       else{
           throw new RuntimeException("User is not a follower");
        }
        if (reviewDTO.getReviewTo() == null) {
            throw new RuntimeException("Review does not have an associated post");
        }

        Post post=postRepository.findById(reviewDTO.getReviewTo())
                                .orElseThrow(()->new RuntimeException("post is not founded"));
        newReview.setReviewTo(post);
        reviewRepository.save(newReview);
        return reviewDTO;

    }

    //--------------------------------remove review -----------------------------------------------
    public void removeReview(Long id, String currentPrincipalName) {
    Review review=reviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("id is not founded"));
    if(!review.getReviewBy().getEmail().equals(currentPrincipalName)){
        throw new RuntimeException("you are not author of this review");
    }
    else{
        reviewRepository.delete(review);
    }
    }
    //--------------------------------All review -------------------------------------------------
    public List<ReviewDTO> getAll() {
        List<Review> reviews = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "reviewId"));

        return reviews.stream()
                .map(review -> {
                    Long postId = review.getReviewTo() != null ? review.getReviewTo().getPostId() : null;
                    String email = review.getReviewBy() != null ? review.getReviewBy().getEmail() : null;

                    return ReviewDTO.builder()
                            .reviewId(review.getReviewId())
                            .reviewBy(email)
                            .reviewTo(postId)
                            .description(review.getDescription())
                            .postedAt(review.getPostedAt())
                            .build();
                })
                .collect(Collectors.toList());
    }

    //--------------------------------remove review ADMIN ------------------------------------------
    public void removeReviewByAdmin(Long id, String mail) {
        Review review=reviewRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("id is not founded"));
        User user=userRepository.findByEmail(mail)
                .orElseThrow(()->new RuntimeException("id is not founded"));
            if(user.getUserRole().equals(UserRole.ADMIN)){
                reviewRepository.delete(review);
            }
        else{
                throw new RuntimeException("you are not author of this review");
            }

    }

}