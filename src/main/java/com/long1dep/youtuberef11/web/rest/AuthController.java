package com.long1dep.youtuberef11.web.rest;

import com.long1dep.youtuberef11.service.dto.AccountDto;
import com.long1dep.youtuberef11.service.dto.request.LoginRequest;
import com.long1dep.youtuberef11.service.dto.request.RegisterAccountRequest;
import com.long1dep.youtuberef11.service.dto.response.LoginResponse;
import com.long1dep.youtuberef11.service.dto.response.Response;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/_api/v1/auth")
public interface AuthController {

    @PostMapping("/login")
    ResponseEntity<Response<LoginResponse>> login(@Valid @RequestBody LoginRequest request);

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response<AccountDto> register(@Valid RegisterAccountRequest request);
}
