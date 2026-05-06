package com.long1dep.youtuberef11.service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
       @NotBlank(message = "Username not blank") String username,
       @NotBlank(message = "Password not blank") String password,
       boolean rememberMe
) {
}
