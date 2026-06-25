package com.long1dep.youtuberef11.web.rest.impl;

import com.long1dep.youtuberef11.service.AccountService;
import com.long1dep.youtuberef11.service.dto.AccountAdminDto;
import com.long1dep.youtuberef11.service.dto.request.ResetPasswordRequest;
import com.long1dep.youtuberef11.service.dto.response.Response;
import com.long1dep.youtuberef11.web.rest.AccountController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;

    @Override
    public Response<List<AccountAdminDto>> getAllAccounts() {
        log.info("======== Get all accounts for admin ========");
        List<AccountAdminDto> accounts = accountService.getAllAccounts();
        return Response.ok(accounts);
    }

    @Override
    public Response<Void> resetPassword(String id, ResetPasswordRequest request) {
        log.info("======== Reset password for account: {} ========", id);
        accountService.resetPassword(id, request.newPassword());
        return Response.ok(null);
    }
}
