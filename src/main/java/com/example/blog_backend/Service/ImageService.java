package com.example.blog_backend.Service;

import com.example.blog_backend.DTO.ImageDTO;
import com.example.blog_backend.Entity.Image;
import com.example.blog_backend.Repository.ImageRepository;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    public Image addImage(MultipartFile image, ImageDTO imageDTO) throws IOException {
        Image newImage=new Image();
        newImage.setLocation(imageDTO.getLocation());
        newImage.setDescription(imageDTO.getDescription());
        if (image != null && !image.isEmpty()) {
            String imageUrl = uploadImageToFirebase(image);
            newImage.setImageUrl(imageUrl);
        }
        return imageRepository.save(newImage);

    }
    private String uploadImageToFirebase(MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();
        // Get Firebase storage instance
        var bucket = StorageClient.getInstance().bucket();
        // Upload the image
        bucket.create(fileName, image.getInputStream(), image.getContentType());
        // Return the public URL
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                bucket.getName(), fileName);
    }
}
