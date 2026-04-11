package com.long1dep.youtuberef11.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message){
        super(message);
    }
}
