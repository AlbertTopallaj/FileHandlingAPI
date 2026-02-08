package com.albert.api_file.exceptions;

public class FolderNotFoundException extends RuntimeException{

    public FolderNotFoundException(String message){
        super(message);
    }

    public FolderNotFoundException(){
        super("No folder found with the given ID");
    }
}
