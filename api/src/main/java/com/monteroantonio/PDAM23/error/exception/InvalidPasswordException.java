package com.monteroantonio.PDAM23.error.exception;

public class InvalidPasswordException extends BadRequestException{

    public InvalidPasswordException(){
        super("La contraseña no es correcta");
    }

}
