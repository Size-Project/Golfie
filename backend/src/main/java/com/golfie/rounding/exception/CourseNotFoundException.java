package com.golfie.rounding.exception;

import com.golfie.common.exception.ApplicationException;
import com.golfie.common.exception.ErrorCode;

public class CourseNotFoundException extends ApplicationException {
    public CourseNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
