package com.long1dep.youtuberef11.web.rest.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode()
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiValidationError<T> implements ApiSubError {
    String object; // chứa obj dto gửi lên bị lỗi

    String field; // lỗi đó ở field nào

    T rejectedValue; // value bị lỗi

    String message; // message chi tiết

    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}



