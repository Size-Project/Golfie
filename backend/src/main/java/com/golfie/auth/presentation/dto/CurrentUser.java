package com.golfie.auth.presentation.dto;

import com.golfie.auth.util.Authority;

public class CurrentUser {
    private final Long id;
    private final Authority authority;

    private CurrentUser(Long id, Authority authority) {
        this.id = id;
        this.authority = authority;
    }

    public static CurrentUser of(Long userId, Authority authority) {
        return new CurrentUser(userId, authority);
    }

    public Long getId() {
        return this.id;
    }

}
