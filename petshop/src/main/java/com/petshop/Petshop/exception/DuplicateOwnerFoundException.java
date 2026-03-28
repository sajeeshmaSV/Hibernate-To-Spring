package com.petshop.Petshop.exception;

public class DuplicateOwnerFoundException extends RuntimeException {
    public DuplicateOwnerFoundException(String message)
    {
        super(message);
    }
}
