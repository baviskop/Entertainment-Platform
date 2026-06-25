package com.long1dep.youtuberef11.service;

import com.long1dep.youtuberef11.service.dto.AccountAdminDto;
import java.util.List;

public interface AccountService {
    List<AccountAdminDto> getAllAccounts();
    void resetPassword(String accountId, String newPassword);
}
