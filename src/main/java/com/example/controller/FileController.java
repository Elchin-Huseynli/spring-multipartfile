package com.example.controller;

import com.example.dto.response.ResponseMessage;
import com.example.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.stream.Stream;


@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileStorageService fileStorageService;

    @GetMapping
    public Stream<Path> getFiles() {
        return fileStorageService.getFiles();
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName) {
        Resource file = fileStorageService.download(fileName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(file);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> upload(@RequestParam MultipartFile file) {   // @RequestParam - binary, @RequestPart - form-data
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileStorageService.upload(file));
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String fileName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileStorageService.delete(fileName));
    }
}
