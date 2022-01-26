package com.golfie.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationExceptionDto> handleBusinessException(final ApplicationException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ApplicationExceptionDto response = ApplicationExceptionDto.of(errorCode);
        return ResponseEntity.status(HttpStatus.valueOf(errorCode.getStatus())).body(response);
    }

}
