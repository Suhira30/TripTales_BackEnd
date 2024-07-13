package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
