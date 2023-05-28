package com.example.handler;

import com.example.dto.response.ResponseMessage;
import com.example.enums.ExceptionEnum;
import com.example.exception.FileDoesntExistsException;
import com.example.exception.FileDownloadIsFailedException;
import com.example.exception.FileUploadIsFailedException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity<ResponseMessage> handleSizeLimitExceededException() {
        return ResponseEntity
                .status(ExceptionEnum.FILE_SIZE_TOO_LARGE.status)
                .body(ResponseMessage.builder()
                        .message(ExceptionEnum.FILE_SIZE_TOO_LARGE.message)
                        .build());
    }

    @ExceptionHandler(FileDoesntExistsException.class)
    public ResponseEntity<ResponseMessage> handleFileDoesntExistsException() {
        return ResponseEntity
                .status(ExceptionEnum.FILE_DOESNT_EXISTS.status)
                .body(ResponseMessage.builder()
                        .message(ExceptionEnum.FILE_DOESNT_EXISTS.message)
                        .build());
    }

    @ExceptionHandler(FileDownloadIsFailedException.class)
    public ResponseEntity<ResponseMessage> handleFileDownloadIsFailedException() {
        return ResponseEntity
                .status(ExceptionEnum.FILE_DOWNLOAD_IS_FAILED.status)
                .body(ResponseMessage.builder()
                        .message(ExceptionEnum.FILE_DOWNLOAD_IS_FAILED.message)
                        .build());
    }

    @ExceptionHandler(FileUploadIsFailedException.class)
    public ResponseEntity<ResponseMessage> handleFileUploadIsFailedException() {
        return ResponseEntity
                .status(ExceptionEnum.FILE_UPLOAD_IS_FAILED.status)
                .body(ResponseMessage.builder()
                        .message(ExceptionEnum.FILE_UPLOAD_IS_FAILED.message)
                        .build());
    }
}
