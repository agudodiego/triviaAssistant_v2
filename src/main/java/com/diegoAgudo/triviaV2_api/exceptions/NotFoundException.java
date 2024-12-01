package com.diegoAgudo.triviaV2_api.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String detail) {
        super(detail);
    }
}
