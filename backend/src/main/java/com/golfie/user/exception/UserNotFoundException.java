package com.golfie.user.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
