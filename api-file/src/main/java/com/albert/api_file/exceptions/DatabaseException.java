package com.albert.api_file.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String message){
        super(message);
    }

    public DatabaseException(){
        super("Database error");
    }
}
