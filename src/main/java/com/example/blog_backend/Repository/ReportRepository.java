package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
