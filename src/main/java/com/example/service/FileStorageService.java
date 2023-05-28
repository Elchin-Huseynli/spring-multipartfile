package com.example.service;

import com.example.dto.response.ResponseMessage;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    void init();
    ResponseMessage upload(MultipartFile file);
    ResponseMessage delete(String fileName);
    Stream<Path> getFiles();
    Resource download(String fileName);
}
