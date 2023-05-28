package com.example.exception;

import com.example.enums.ExceptionEnum;

public class FileDownloadIsFailedException extends RuntimeException{
    public FileDownloadIsFailedException() {
        super(ExceptionEnum.FILE_DOWNLOAD_IS_FAILED.message);
    }
}
