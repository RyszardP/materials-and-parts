package com.ryszard.materialsandparts.exception;

public class NoSuchEntityException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoSuchEntityException(String message){
        super(message);
    }

    public NoSuchEntityException(String message, Exception ex){
        super(message, ex);
    }
}
