package com.albert.api_file.exceptions;

public class PasswordErrorException extends RuntimeException {

    public PasswordErrorException(String message){
        super(message);
    }

    public PasswordErrorException(){
        super("Password is missing or its shorter than 8 characters");
    }
}
