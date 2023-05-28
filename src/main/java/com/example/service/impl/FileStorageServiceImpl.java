package com.example.service.impl;

import com.example.dto.response.ResponseMessage;
import com.example.enums.ExceptionEnum;
import com.example.enums.SuccessMessageEnum;
import com.example.exception.FileDoesntExistsException;
import com.example.exception.FileDownloadIsFailedException;
import com.example.exception.FileUploadIsFailedException;
import com.example.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;


@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {

    private final Path ROOT = Paths.get("upload");

    @Override
    public void init() {
        try {
            Path directory = Files.createDirectory(ROOT);
            log.info("Path: {}", directory.getRoot());
        }
        catch (IOException e) {
            log.error("Directory created failed!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseMessage upload(MultipartFile file) {
        StringBuilder message = new StringBuilder();
        try {
            Files.copy(file.getInputStream(), this.ROOT.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            message.append(SuccessMessageEnum.FILE_UPLOAD_IS_SUCCESSFULLY.message);
            log.info(SuccessMessageEnum.FILE_UPLOAD_IS_SUCCESSFULLY.message);
        }
        catch (IOException e) {
            log.error(ExceptionEnum.FILE_UPLOAD_IS_FAILED.message);
            throw new FileUploadIsFailedException();
        }

        return ResponseMessage.builder()
                .message(message.toString())
                .build();
    }

    @Override
    public ResponseMessage delete(String fileName) {
        StringBuilder message = new StringBuilder();
        try {
            if (Files.deleteIfExists(ROOT.resolve(fileName))) {
                message.append(SuccessMessageEnum.FILE_DELETE_SUCCESSFULLY.message);
                log.info(SuccessMessageEnum.FILE_DELETE_SUCCESSFULLY.message);
            }
            else {
                message.append(ExceptionEnum.FILE_DOESNT_EXISTS.message);
                log.error(ExceptionEnum.FILE_DOESNT_EXISTS.message);
            }
        } catch (IOException e) {
            throw new FileDoesntExistsException();
        }
        return ResponseMessage.builder()
                .message(message.toString())
                .build();
    }

    @Override
    public Stream<Path> getFiles() {
        try {
             return Files.walk(ROOT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource download(String fileName) {
        Path file = ROOT.resolve(fileName);

        try {
            Resource resource = new  UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                log.info("bytes: {}", resource.getInputStream());
                return resource;
            }
            else {
                log.error(ExceptionEnum.FILE_DOWNLOAD_IS_FAILED.message);
                throw new FileDownloadIsFailedException();
            }
        }
        catch (IOException e) {
            throw new FileDownloadIsFailedException();
        }
    }
}
