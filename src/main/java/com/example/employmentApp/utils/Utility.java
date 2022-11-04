package com.example.employmentApp.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Utility {

    public static String saveFile(MultipartFile file, String path) {
        String originalFileName = file.getOriginalFilename();
        originalFileName = originalFileName.replace(" ", "-");
        String finalName = randomAlphaNumeric(8) + originalFileName;
        try {
            Path destinationFile = Paths.get(path + finalName);
            System.out.println("File: " + destinationFile.toAbsolutePath());
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return finalName;
        }catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public static String randomAlphaNumeric(int count) {
        String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count--!= 0) {
            int character = (int) (Math.random() * CARACTERES.length());
            builder.append(CARACTERES.charAt(character));
        }
        return builder.toString();
    }
}
