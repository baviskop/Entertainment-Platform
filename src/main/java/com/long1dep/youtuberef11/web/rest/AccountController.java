package com.long1dep.youtuberef11.web.rest;

import com.long1dep.youtuberef11.service.dto.AccountAdminDto;
import com.long1dep.youtuberef11.service.dto.request.ResetPasswordRequest;
import com.long1dep.youtuberef11.service.dto.response.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/_api/v1/admin/accounts")
public interface AccountController {

    @Secured("ROLE_ADMIN")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Response<List<AccountAdminDto>> getAllAccounts();

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}/reset-password")
    @ResponseStatus(HttpStatus.OK)
    Response<Void> resetPassword(
            @PathVariable("id") String id,
            @Valid @RequestBody ResetPasswordRequest request
    );
}
