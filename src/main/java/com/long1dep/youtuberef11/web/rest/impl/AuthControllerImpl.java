package com.long1dep.youtuberef11.web.rest.impl;

import com.long1dep.youtuberef11.service.AuthService;
import com.long1dep.youtuberef11.service.dto.request.LoginRequest;
import com.long1dep.youtuberef11.service.dto.response.LoginResponse;
import com.long1dep.youtuberef11.service.dto.response.Response;
import com.long1dep.youtuberef11.web.rest.AuthController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public Response<LoginResponse> login(LoginRequest request) {
        return Response.ok(authService.login(request));
    }
}
