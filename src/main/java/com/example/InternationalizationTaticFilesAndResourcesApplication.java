package com.example;

import com.example.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class InternationalizationTaticFilesAndResourcesApplication implements CommandLineRunner {

    private final FileStorageService fileStorageService;

    public static void main(String[] args) {
        SpringApplication.run(InternationalizationTaticFilesAndResourcesApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        fileStorageService.init();
    }
}
