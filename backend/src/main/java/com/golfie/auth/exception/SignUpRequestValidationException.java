package com.golfie.auth.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class SignUpRequestValidationException extends ApplicationException {

    public SignUpRequestValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
