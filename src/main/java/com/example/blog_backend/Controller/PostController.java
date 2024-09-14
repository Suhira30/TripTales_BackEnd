package com.example.blog_backend.Controller;

import com.example.blog_backend.DTO.PostDTO;
import com.example.blog_backend.Entity.Post;
import com.example.blog_backend.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
private final PostService postService;
    @PostMapping("/add")
    public ResponseEntity<Post> addPost(@RequestBody PostDTO postDTO){
        Post newPost=postService.addPost(postDTO);
        return ResponseEntity.ok(newPost);
    }
//    @GetMapping("/latest")
//    public ResponseEntity<PostDTO> getLatestPost(){
//        List<PostDTO> latestPost=postService.getLatestPost();
//        return ResponseEntity.ok((PostDTO) latestPost);
//    }

}
