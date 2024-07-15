package com.example.blog_backend.Auth.Entity;

import com.example.blog_backend.Auth.Entity.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User  implements UserDetails {
    @Id
    @Column(nullable = false,unique = true)
    @Email(message = "Enter the  valid email ")
    private String email;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 6,message = "Password can not be less than 6 letters")
    private String password;

    @NotBlank(message = "Can't be blank")
    private String name;

    @NotBlank(message = "Can't be blank")
    @Column(unique = true)
    private String mobileNo;

    @Enumerated(EnumType.STRING)
    UserRole userRole;

    private boolean isAccountNonExpired=true;
    private boolean isAccountNonLocked=true;
    private boolean isCredentialsNonExpired=true;
    private boolean isEnabled =true;

    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
