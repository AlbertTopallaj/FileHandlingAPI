package com.albert.api_file.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message){
        super(message);
    }

    public UserAlreadyExistsException(){
        super("The username is already taken");
    }
}
