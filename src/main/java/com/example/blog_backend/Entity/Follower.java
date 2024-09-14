package com.example.blog_backend.Entity;

import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Entity.Report;
import com.example.blog_backend.Entity.Review;
import com.example.blog_backend.Entity.Subscription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class Follower extends User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long followerId;
    private boolean subscriptionStatus=false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id",referencedColumnName = "subId")
    private Subscription subscription;

    @JsonIgnore
    @OneToMany(mappedBy = "reviewBy", cascade = CascadeType.DETACH,orphanRemoval = true)
    private List<Review> review=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "reportBy",cascade = CascadeType.DETACH,orphanRemoval = true)
    private List<Report> reports=new ArrayList<>();



}
