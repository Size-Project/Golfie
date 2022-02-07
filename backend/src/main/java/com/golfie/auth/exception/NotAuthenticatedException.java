package com.golfie.auth.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class NotAuthenticatedException extends ApplicationException {

    public NotAuthenticatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
