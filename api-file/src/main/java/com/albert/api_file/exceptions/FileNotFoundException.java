package com.albert.api_file.exceptions;

public class FileNotFoundException extends RuntimeException{

    public FileNotFoundException(String message){
        super(message);
    }

    public FileNotFoundException(){
        super("No file found with the given ID");
    }
}
