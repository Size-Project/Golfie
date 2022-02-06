package com.golfie.common.factory;

import com.golfie.common.fixture.TUser;

import java.util.concurrent.atomic.AtomicLong;

public class TUserFactory {

    private final AtomicLong sequence;
    private final int port;

    public TUserFactory(int port) {
        this.port = port;
        this.sequence = new AtomicLong(1);
    }

    public TUser createOtherUser() {
        return new TUser(port, sequence.getAndAdd(1), "other");
    }

    public TUser createMember() {
        return new TUser(port, sequence.getAndAdd(1), "me");
    }

    public TUser createGuest() {
        return new TUser(port);
    }
}
