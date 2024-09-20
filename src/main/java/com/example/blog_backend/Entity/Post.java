package com.example.blog_backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(length = 2000)
    private String guide;

    @Column(length = 2000)
    private String experience;

    private LocalDateTime postedOn=LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Continent continent;

    private String location;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    List<Category> category;
    private String imgUrl;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="admin_email",referencedColumnName = "email")
    private Admin postBy;

    @JsonIgnore
    @OneToMany(mappedBy = "reviewTo",cascade = CascadeType.DETACH,orphanRemoval = true)
    private List<Review> review=new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL)
//    private List<Image> images=new ArrayList<>();
//    private String imgUrl;


    @JsonIgnore
    @OneToMany(mappedBy = "reportedTo" ,cascade = CascadeType.DETACH,orphanRemoval = true)
    private List<Report> reports=new ArrayList<>();

}
