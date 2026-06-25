package com.long1dep.youtuberef11.service;

import com.long1dep.youtuberef11.service.dto.AccountDto;
import com.long1dep.youtuberef11.service.dto.request.LoginRequest;
import com.long1dep.youtuberef11.service.dto.request.RegisterAccountRequest;
import com.long1dep.youtuberef11.service.dto.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);

    AccountDto register(RegisterAccountRequest request);

    void Logout(String token);
}
