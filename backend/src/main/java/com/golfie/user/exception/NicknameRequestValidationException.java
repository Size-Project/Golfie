package com.golfie.user.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class NicknameRequestValidationException extends ApplicationException {

    public NicknameRequestValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
