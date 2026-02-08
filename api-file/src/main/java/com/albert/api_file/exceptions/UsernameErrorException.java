package com.albert.api_file.exceptions;

public class UsernameErrorException extends RuntimeException {

    public UsernameErrorException(String message){
        super(message);
    }

    public UsernameErrorException(){
        super("Username is missing or its shorter than 4 characters");
    }
}
