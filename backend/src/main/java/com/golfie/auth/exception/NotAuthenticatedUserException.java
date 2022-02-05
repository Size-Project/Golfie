package com.golfie.auth.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class NotAuthenticatedUserException extends ApplicationException {

    public NotAuthenticatedUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
