package com.monteroantonio.PDAM23.security.jwt.refresh;

import com.monteroantonio.PDAM23.security.error.JwtTokenException;

public class RefreshTokenException extends JwtTokenException {

    public RefreshTokenException(String msg) {
        super(msg);
    }

}
