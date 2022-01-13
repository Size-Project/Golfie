package com.golfie.auth.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class NaverApiAccessException extends ApplicationException {

    public NaverApiAccessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
