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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        Post post=postRepository.findById(reviewDTO.getPostId())
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
        List<Review> reviews= reviewRepository.findAllByOrderByReviewIdDesc();
        return reviews.stream().map(this::convertToDTO).toList();
    }
    private ReviewDTO convertToDTO(Review review) {
        return new ReviewDTO(
                review.getReviewId(),
                review.getDescription(),
                review.getPostedAt(),
                review.getReviewTo().getPostId(), // Assuming Post has a getPostId method
                review.getReviewBy().getEmail()   // Assuming Follower has a getEmail method
        );
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