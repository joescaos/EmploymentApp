package com.example.employmentApp.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Utilery {

    public static String saveFile(MultipartFile file, String path) {
        String originalFileName = file.getOriginalFilename();
        try {
            Path destinationFile = Paths.get(path+originalFileName);
            System.out.println("File: " + destinationFile.toAbsolutePath());
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return originalFileName;
        }catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
}
