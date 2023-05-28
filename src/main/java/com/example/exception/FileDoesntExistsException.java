package com.example.exception;

import com.example.enums.ExceptionEnum;

public class FileDoesntExistsException extends RuntimeException{
    public FileDoesntExistsException() {
        super(ExceptionEnum.FILE_DOESNT_EXISTS.message);
    }
}
