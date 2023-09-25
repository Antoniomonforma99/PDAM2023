package com.monteroantonio.pdam.PDAM.security.error;

public class JwtTokenException extends RuntimeException{

    public JwtTokenException(String msg) {
        super(msg);
    }
}
