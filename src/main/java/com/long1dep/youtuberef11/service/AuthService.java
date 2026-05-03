package com.long1dep.youtuberef11.service;

import com.long1dep.youtuberef11.service.dto.AccountDto;
import com.long1dep.youtuberef11.service.dto.request.AccountRegisterRequest;
import com.long1dep.youtuberef11.service.dto.request.LoginRequest;
import com.long1dep.youtuberef11.service.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    AccountDto register(AccountRegisterRequest request);
}
