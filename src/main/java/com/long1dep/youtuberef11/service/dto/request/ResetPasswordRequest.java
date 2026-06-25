package com.long1dep.youtuberef11.service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(
    @NotBlank(message = "Mật khẩu mới không được để trống") String newPassword
) {
}
