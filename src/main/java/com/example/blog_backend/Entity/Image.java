package com.example.blog_backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;
//    @ManyToOne
//    @JoinColumn(name="post_id",referencedColumnName = "postId")
//    private Post post;
    private String imageUrl;
    private String location;
    private String description;


}
