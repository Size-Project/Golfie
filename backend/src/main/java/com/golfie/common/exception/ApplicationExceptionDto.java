package com.golfie.common.exception;

public class ApplicationExceptionDto {

    private String code;
    private String message;

    public ApplicationExceptionDto() {}

    public ApplicationExceptionDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApplicationExceptionDto of(ErrorCode errorCode) {
        return new ApplicationExceptionDto(errorCode.getCode(), errorCode.getMessage());
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
