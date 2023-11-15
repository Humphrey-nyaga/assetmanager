package com.assetmanager.exceptions;

public class AssetNotFoundException extends RuntimeException{
    public AssetNotFoundException(String msg){
        super(msg);
    }
}
