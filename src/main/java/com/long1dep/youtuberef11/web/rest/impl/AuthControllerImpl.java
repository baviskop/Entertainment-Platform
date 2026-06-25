package com.long1dep.youtuberef11.web.rest.impl;

import com.long1dep.youtuberef11.service.AuthenticationService;
import com.long1dep.youtuberef11.service.dto.AccountDto;
import com.long1dep.youtuberef11.service.dto.request.LoginRequest;
import com.long1dep.youtuberef11.service.dto.request.RegisterAccountRequest;
import com.long1dep.youtuberef11.service.dto.response.LoginResponse;
import com.long1dep.youtuberef11.service.dto.response.Response;
import com.long1dep.youtuberef11.web.rest.AuthController;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<Response<LoginResponse>> login(LoginRequest request) {
        final var loginResponse = authenticationService.login(request);
        final var httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + loginResponse.token());
        return new ResponseEntity<>(Response.ok(loginResponse), httpHeaders, HttpStatus.OK);
    }

    @Override
    public Response<AccountDto> register(RegisterAccountRequest request) {
        return Response
                .created(authenticationService.register(request));
    }

    @Override
    public ResponseEntity<Response<Void>> logout(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            authenticationService.Logout(token);
        }
        return new ResponseEntity<>(Response.ok(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<AccountDto>> getMe() {
        return new ResponseEntity<>(Response.ok(authenticationService.getCurrentUser()), HttpStatus.OK);
    }
}
