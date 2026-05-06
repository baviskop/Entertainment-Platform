package com.long1dep.youtuberef11.common.constants;

import com.long1dep.youtuberef11.web.rest.error.MessageCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppConstant {
    public static final MessageCode SERVICE_ERROR = new MessageCode("500", "Service Error");
    public static final MessageCode BAD_REQUEST = new MessageCode("400", "Bad Request");
    public static final MessageCode FORBIDDEN = new MessageCode("403", "Forbidden");
    public static final String SYSTEM = "system";
}
