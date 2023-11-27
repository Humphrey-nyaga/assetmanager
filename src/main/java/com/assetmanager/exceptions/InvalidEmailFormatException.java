package com.assetmanager.exceptions;

public class InvalidEmailFormatException extends RuntimeException{
    public InvalidEmailFormatException(String msg){
        super(msg);
    }
}
