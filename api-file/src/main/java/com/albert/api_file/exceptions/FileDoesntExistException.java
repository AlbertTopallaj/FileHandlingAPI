package com.albert.api_file.exceptions;

public class FileDoesntExistException extends RuntimeException {

    public FileDoesntExistException(String message) {
        super(message);
    }

    public FileDoesntExistException() {
        super("The file does not exist");
    }
}
