package com.example.exception;

import com.example.enums.ExceptionEnum;

public class FileUploadIsFailedException extends RuntimeException{
    public FileUploadIsFailedException() {
        super(ExceptionEnum.FILE_UPLOAD_IS_FAILED.message);
    }
}
