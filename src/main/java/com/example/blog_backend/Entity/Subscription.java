package com.example.blog_backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subId;

    @Enumerated(EnumType.STRING)
    SubscriptionStatus status;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToOne(mappedBy = "subscription")
    private Follower follower;

//    @OneToOne(mappedBy = "payment")
//    private Payment payment;
    private String stripeCustomerId; // To store Stripe customer ID
    private String stripeSubscriptionId; // To store Stripe subscription ID
    private Double amount;
}
