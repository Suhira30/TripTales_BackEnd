package com.example.blog_backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    private String description;

    private LocalDateTime postedOn=LocalDateTime.now();

    private String location;

    @Enumerated(EnumType.STRING)
    List<Category> category;

    @ManyToOne
    @JoinColumn(name="admin_email",referencedColumnName = "email")
    private Admin postBy;

    @JsonIgnore
    @OneToMany(mappedBy = "reviewTo",cascade = CascadeType.DETACH,orphanRemoval = true)
    private List<Review> review=new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL)
//    private List<Image> images=new ArrayList<>();

    @Lob
    private Blob img;

    @JsonIgnore
    @OneToMany(mappedBy = "reportedTo" ,cascade = CascadeType.DETACH,orphanRemoval = true)
    private List<Report> reports=new ArrayList<>();

    public void setPostBy(String email) {
        this.setPostBy(email);
    }



//    public void setPostBy(String email) {
//    }
//
//    }
}
