package com.albert.api_file.exceptions;

public class FolderDoesntExistException extends RuntimeException{

    public FolderDoesntExistException(String message){
        super(message);
    }

    public FolderDoesntExistException(){
        super("The folder does not exist");
    }
}
