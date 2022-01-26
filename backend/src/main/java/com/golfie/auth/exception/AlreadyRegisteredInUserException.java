package com.golfie.auth.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class AlreadyRegisteredInUserException extends ApplicationException {

    public AlreadyRegisteredInUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
