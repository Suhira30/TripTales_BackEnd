package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
}
