package com.long1dep.youtuberef11.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterAccountRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
    MultipartFile avatar;
}
