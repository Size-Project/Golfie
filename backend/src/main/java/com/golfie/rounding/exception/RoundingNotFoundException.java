package com.golfie.rounding.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class RoundingNotFoundException extends ApplicationException {
    public RoundingNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
