package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.response.FileUploadResponse;
import com.tin.springcoursera.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class FileStorageController {
    private final FileStorageService fileStorageService;

    @PostMapping("upload")
    public FileUploadResponse upload(
            @RequestParam("file") MultipartFile file) throws IOException {
        String fileUrl = fileStorageService.storeFile(file);
        String baseUrl = "http://localhost:8080/uploads/";
        return new FileUploadResponse(baseUrl + fileUrl);
    }
}
