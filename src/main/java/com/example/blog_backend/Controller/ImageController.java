package com.example.blog_backend.Controller;

import com.example.blog_backend.DTO.ImageDTO;
import com.example.blog_backend.Entity.Image;
import com.example.blog_backend.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*0")
@RequestMapping("/api/v1/image")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @PostMapping("/add")
    public ResponseEntity<Image> addImage(@RequestPart("image") MultipartFile image , @RequestPart("description")ImageDTO imageDTO)throws IOException {
        Image newImage=imageService.addImage(image,imageDTO);
        return ResponseEntity.ok(newImage);
    }
}
