//package com.example.blog_backend.Entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDate;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//public class Payment {
//    @Id
//    private Long paymentId;
//
//    private String paymentProvider;  // e.g., "Stripe"
//    private String transactionId;    // Payment gateway transaction ID
//    private Double amount;
//    private LocalDate paymentDate;
//
//    @OneToOne
//    @JoinColumn(name = "sub_id",referencedColumnName = "subId")
//    private Subscription subscription;
//
//
//}
