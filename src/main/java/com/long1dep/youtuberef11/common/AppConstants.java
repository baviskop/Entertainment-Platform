package com.long1dep.youtuberef11.common;

import com.long1dep.youtuberef11.web.rest.error.MessageCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.tools.Diagnostic;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppConstants {
    public static final MessageCode SERVICE_ERROR = new MessageCode("500", "Service Error");
    public static final MessageCode BAD_REQUEST = new MessageCode("400", "Bad Request");
}
