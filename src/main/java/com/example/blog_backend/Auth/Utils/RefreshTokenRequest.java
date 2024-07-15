package com.example.blog_backend.Auth.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class RefreshTokenRequest {
    private String refreshToken;

}
