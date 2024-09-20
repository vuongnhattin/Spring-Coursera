package com.tin.springcoursera.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileStorageService {
    public String storeFile(MultipartFile file) throws IOException {
        String currentTime = String.valueOf(System.currentTimeMillis());
        String fileName = currentTime + "-" + file.getOriginalFilename().replaceAll(" ", "_");
        String path = System.getProperty("user.dir") + "/uploads/" + fileName;
        File uploadedFile = new File(path);
        file.transferTo(uploadedFile);

        return fileName;
    }
}
