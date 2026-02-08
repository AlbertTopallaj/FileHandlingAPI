package com.albert.api_file.exceptions;

public class MissingFileNameException extends RuntimeException{

    public MissingFileNameException(String message){
        super(message);
    }

    public MissingFileNameException(){
        super("File name is missing");
    }

}
