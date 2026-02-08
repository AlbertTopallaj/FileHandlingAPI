package com.albert.api_file.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{

    public InvalidUserCredentialsException(String message){
        super(message);
    }

    public InvalidUserCredentialsException(){
        super("Invalid username or password");
    }
}
