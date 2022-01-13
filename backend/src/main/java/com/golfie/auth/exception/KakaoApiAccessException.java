package com.golfie.auth.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class KakaoApiAccessException extends ApplicationException {

    public KakaoApiAccessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
