package com.albert.api_file.exceptions;

public class FileIsEmptyException extends RuntimeException{

    public FileIsEmptyException(String message){
        super(message);
    }

    public FileIsEmptyException(){
        super("The file is empty");
    }
}
