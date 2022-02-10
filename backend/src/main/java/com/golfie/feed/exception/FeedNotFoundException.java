package com.golfie.feed.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class FeedNotFoundException extends ApplicationException {

    public FeedNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
