package com.monteroantonio.PDAM23.security.error;

public class JwtTokenException extends RuntimeException{
    public JwtTokenException(String msg) {
        super(msg);
    }

}

