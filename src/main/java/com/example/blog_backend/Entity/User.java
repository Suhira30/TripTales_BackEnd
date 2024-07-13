package com.example.blog_backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Inheritance(strategy = InheritanceType.JOINED)

public class User {
    @Id
    @Column(nullable = false,unique = true)
    @Email(message = "Enter the  valid email ")
    private String email;

    @NotBlank(message = "Can't be blank")
    private String name;

    @NotBlank(message = "Can't be blank")
    @Column(unique = true)
    private String mobileNo;

    @Enumerated(EnumType.STRING)
    UserRole userRole;


}
