package com.monteroantonio.pdam.PDAM.security.jwt.refresh;

import com.monteroantonio.pdam.PDAM.security.error.JwtTokenException;

public class RefreshTokenException extends JwtTokenException {


    public RefreshTokenException(String msg) {
        super(msg);
    }
}
