package com.golfie.user.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class DuplicatedNicknameException extends ApplicationException {

    public DuplicatedNicknameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
