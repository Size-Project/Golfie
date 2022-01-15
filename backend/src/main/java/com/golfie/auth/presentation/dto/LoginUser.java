package com.golfie.auth.presentation.dto;

import com.golfie.auth.util.Authority;

public class LoginUser {
    private final Long id;
    private final Authority authority;

    public LoginUser(Long id, Authority authority) {
        this.id = id;
        this.authority = authority;
    }

    public static LoginUser of(Long userId, Authority authority) {
        return new LoginUser(userId, authority);
    }

    public Long getId() {
        return this.id;
    }

    public Authority getAuthority() {
        return authority;
    }
}
