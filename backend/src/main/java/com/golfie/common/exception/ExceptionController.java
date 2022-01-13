package com.golfie.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationExceptionDto> handleBusinessException(final ApplicationException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ApplicationExceptionDto response = ApplicationExceptionDto.of(errorCode);
        return ResponseEntity.badRequest().body(response);
    }

}
