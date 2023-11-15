package com.assetmanager.exceptions;

public class UserPasswordEncodingFailed extends RuntimeException {
    public UserPasswordEncodingFailed(String message){
        super(message);
    }
}
