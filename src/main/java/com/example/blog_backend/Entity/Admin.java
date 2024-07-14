package com.example.blog_backend.Entity;

import com.example.blog_backend.Auth.Entity.User;
import com.example.blog_backend.Entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Admin extends User {
    private Long companyId;

    @JsonIgnore
    @OneToMany(mappedBy = "postBy",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> post=new ArrayList<>();
}
