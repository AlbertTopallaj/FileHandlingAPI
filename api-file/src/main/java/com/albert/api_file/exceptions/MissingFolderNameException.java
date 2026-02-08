package com.albert.api_file.exceptions;

public class MissingFolderNameException extends RuntimeException{

    public MissingFolderNameException(String message){
        super(message);
    }

    public MissingFolderNameException(){
        super("The folder name is missing");
    }
}
