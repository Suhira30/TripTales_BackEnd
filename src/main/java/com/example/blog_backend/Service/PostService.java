package com.example.blog_backend.Service;

import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Auth.Repository.UserRepository;
import com.example.blog_backend.DTO.PostDTO;
import com.example.blog_backend.Entity.Admin;
import com.example.blog_backend.Entity.Post;
import com.example.blog_backend.Repository.AdminRepository;
import com.example.blog_backend.Repository.PostRepository;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;

import java.time.LocalDateTime;
import java.util.Optional;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    PostRepository postRepository;


    @Autowired
    UserRepository userRepository;

    private String getAuthenticatedAdminEmail(){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
           UserDetails userDetails=(UserDetails) authentication.getPrincipal();
           return userDetails.getUsername();
       }
       throw new RuntimeException("No authenticated user found ");
    }
    //--------------------------------ADD POST---------------------------------------------
    public Post addPost(PostDTO postDTO, MultipartFile imageFile) throws IOException {
        Post newPost=new Post();
        newPost.setTitle(postDTO.getTitle());
        newPost.setExperience(postDTO.getExperience());
        newPost.setGuide(postDTO.getGuide());
        newPost.setLocation(postDTO.getLocation());
        newPost.setCategory(postDTO.getCategory());
//        newPost.setContinent(postDTO.getContinent());
        newPost.setPostedOn(LocalDateTime.now());
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = uploadImageToFirebase(imageFile);
            newPost.setImgUrl(imageUrl);
        }
        String adminEmail = getAuthenticatedAdminEmail();
        Optional<User> adminOpt = userRepository.findByEmail(adminEmail);
        if (adminOpt.isPresent()) {
            newPost.setPostBy((Admin) adminOpt.get()); // Ensure postBy is an Admin object
        } else {
            throw new RuntimeException("Admin not found with email: " + adminEmail);
        }
        return postRepository.save(newPost);
    }

    private String uploadImageToFirebase(MultipartFile imageFile) throws IOException{
        String fileName = UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();
        // Get Firebase storage instance
        var bucket = StorageClient.getInstance().bucket();
        // Upload the image
        bucket.create(fileName, imageFile.getInputStream(), imageFile.getContentType());
        // Return the public URL
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                bucket.getName(), fileName);
    }
    //--------------------------------NO OF POST---------------------------------------------
    public int getNoOfPost() {
    int tot= (int) postRepository.count();
        return tot;
    }
    //--------------------------------LAST POST---------------------------------------------
    public PostDTO getLastPost() {
        Post lastPost=postRepository.findTopByOrderByPostedOnDesc();
        return covertToDTO(lastPost);
    }
    public PostDTO covertToDTO(Post lastPost){
        return new PostDTO(
          lastPost.getTitle(),
//          lastPost.getContinent(),
          lastPost.getImgUrl(),
          lastPost.getLocation(),
          lastPost.getPostedOn()
        );
    }

}
