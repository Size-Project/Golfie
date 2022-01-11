package com.golfie.auth.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class UnsupportedSocialProviderException extends ApplicationException {

    public UnsupportedSocialProviderException(ErrorCode errorCode) {
        super(errorCode);
    }
}
