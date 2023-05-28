package com.example.enums;

public enum ExceptionEnum {
    FILE_SIZE_TOO_LARGE(500, "File size too large!"),
    FILE_UPLOAD_IS_FAILED(400,"File upload is failed!"),
    FILE_DOESNT_EXISTS(404, "File doesn't exists!"),
    FILE_DOWNLOAD_IS_FAILED(400,"File download is failed!");

    public int status;
    public String message;

    ExceptionEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
