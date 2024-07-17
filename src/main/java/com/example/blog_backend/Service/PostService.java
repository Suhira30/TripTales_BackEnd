package com.example.blog_backend.Service;

import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.DTO.PostDTO;
import com.example.blog_backend.Entity.Post;
import com.example.blog_backend.Repository.AdminRepository;
import com.example.blog_backend.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    PostRepository postRepository;
    public Post addPost(PostDTO postDTO) {
        String adminMail = getAuthenticatedAdminEmail();
//        Post savedPost;
        Optional<User> admin = adminRepository.findByEmail(adminMail);
        if(admin.isPresent()) {
            Post post = new Post();
            post.setTitle(postDTO.getTitle());
            post.setDescription(postDTO.getDescription());
            post.setLocation(postDTO.getLocation());
            post.setCategory(postDTO.getCategory());
            post.setImages(postDTO.getImages());
            post.setPostedOn(LocalDateTime.now());
            post.setPostBy(admin.get().getEmail());
            Post savedPost = postRepository.save(post);
            System.out.println(savedPost);
            return savedPost;
        } else  {
            throw new RuntimeException("Admin is not found");
        }
    }
    private String getAuthenticatedAdminEmail(){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
           UserDetails userDetails=(UserDetails) authentication.getPrincipal();
           return userDetails.getUsername();
       }
       throw new RuntimeException("No authenticated user found ");
    }

    public List<PostDTO> getLAtestPost() {
        List<Post> posts= postRepository.findTop10ByOrderByIdDesc();
        List<PostDTO> postDTOs = posts.stream().map(this::convertToDTO).collect(Collectors.toList());
        return postDTOs;
    }

    private PostDTO convertToDTO(Post post) {
    PostDTO postDTO=new PostDTO();
        postDTO.setTitle(post.getTitle());
        postDTO.setLocation(post.getLocation());
        return postDTO;
    }
}
