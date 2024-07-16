package com.example.blog_backend.Controller;

import com.example.blog_backend.DTO.PostDTO;
import com.example.blog_backend.Entity.Post;
import com.example.blog_backend.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
private final PostService postService;
    @PostMapping("/add")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Post> addPost(@RequestBody PostDTO postDTO){
        Post newPost=postService.addPost(postDTO);
        return ResponseEntity.ok(newPost);
    }

}
