package com.example.blog_backend.Controller;

import com.example.blog_backend.DTO.PostDTO;
import com.example.blog_backend.Entity.Category;
import com.example.blog_backend.Entity.Continent;
import com.example.blog_backend.Entity.Post;
import com.example.blog_backend.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private final PostService postService;

    //--------------------------------ADD POST---------------------------------------------
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Post> addPost(@RequestPart("postDTO") PostDTO postDTO, @RequestPart("imageFile") MultipartFile imageFile) throws IOException {
        Post newPost = postService.addPost(postDTO, imageFile);
        return ResponseEntity.ok(newPost);
    }

    //--------------------------------NO OF POST---------------------------------------------
    @GetMapping("/noof_post")
    public ResponseEntity<Integer> getNoOfPost() {
        int tot = postService.getNoOfPost();
        return ResponseEntity.ok(tot);
    }

    //--------------------------------LAST POST---------------------------------------------
    @GetMapping("/lastPost")
    public ResponseEntity<PostDTO> getLastPost() {
//        PostDTO lastPost=postService.getLastPost();
        return ResponseEntity.ok(postService.getLastPost());
    }

    //--------------------------------POST BY CATEGORY---------------------------------------------
    @GetMapping("/postByCategory/{category}")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable("category") Category category) {
        return ResponseEntity.ok(postService.getPostByCategory(category));
    }
    //--------------------------------POST BY GEOGRAPHICAL AREA---------------------------------------
    @GetMapping("/postByArea/{continent}")
    public ResponseEntity<List<PostDTO>> getPostByArea(@PathVariable("continent") Continent continent) {
        return ResponseEntity.ok(postService.getPostByArea(continent));
    }
    //--------------------------------EACH POST DETAIL---------------------------------------
    @GetMapping("/eachPost/{postId}")
    public ResponseEntity<PostDTO> getEachPostDetail(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getEachPostDetail(postId));
    }

    //------------------------------------------ALL POST---------------------------------------------
    @GetMapping("/allpost")
    public ResponseEntity<List<PostDTO>> getAllPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    //------------------------------------------POPULAR POST---------------------------------------------
    @GetMapping("/poppularPost")
    public ResponseEntity<List<PostDTO>> getPoppularPost() {
        return ResponseEntity.ok(postService.getPoppularPost());
    }
}