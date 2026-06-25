package com.long1dep.youtuberef11.service.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountAdminDto {
    String id;
    String username;
    String avatar;
    List<String> roles;
}
