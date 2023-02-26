package com.ivomtdias.springshopapi.exception;

public class EmailException extends RuntimeException{
    public EmailException(){
        super("Unexpected error occurred!");
    }
}
