package com.assetmanager.exceptions;

public class AssetAlreadyAssignedException extends RuntimeException{
    public AssetAlreadyAssignedException(String msg){
        super(msg);
    }
}
