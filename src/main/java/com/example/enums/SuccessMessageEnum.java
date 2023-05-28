package com.example.enums;

public enum SuccessMessageEnum {
    FILE_UPLOAD_IS_SUCCESSFULLY("File upload is successfully!"),
    FILE_DELETE_SUCCESSFULLY("File delete successfully!");

    public String message;

    SuccessMessageEnum(String message) {
        this.message = message;
    }
}
