package com.example.blog_backend.Entity;

import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Entity.Report;
import com.example.blog_backend.Entity.Review;
import com.example.blog_backend.Entity.Subscription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Follower extends User {

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
