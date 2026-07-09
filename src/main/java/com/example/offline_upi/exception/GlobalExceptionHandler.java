package com.example.offline_upi.exception;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public ApiError handleRuntimeException(RuntimeException ex){
        return ApiError.builder()
                       .message(ex.getMessage())
                       .status("Failed")
                       .timeStamp(LocalDateTime.now())
                       .build();
    }
}
