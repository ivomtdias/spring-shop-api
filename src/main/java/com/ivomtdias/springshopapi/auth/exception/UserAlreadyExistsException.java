package com.ivomtdias.springshopapi.auth.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String email){
        super(String.format("User with email: %s already exists!", email));
    }
}
