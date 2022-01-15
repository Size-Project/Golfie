package com.golfie.common.exception;

public enum ErrorCode {

    // Authentication
    UNSUPPORTED_SOCIAL_PROVIDER(400, "A001", "지원하지 않는 소셜 로그인 서비스입니다."),
    KAKAO_ACCESS(500, "A002", "카카오 로그인 서비스에 접근 중 에러가 발생하였습니다."),
    NAVER_ACCESS(500, "A003", "네이버 로그인 서비스에 접근 중 에러가 발생하였습니다."),

    // User
    USER_NOT_FOUND(404, "U001", "존재하지 않는 회원입니다."),

    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
