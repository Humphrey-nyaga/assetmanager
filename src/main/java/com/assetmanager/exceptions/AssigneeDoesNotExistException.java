package com.assetmanager.exceptions;

public class AssigneeDoesNotExistException extends RuntimeException{
    public AssigneeDoesNotExistException(String message){
        super(message);
    }
}
