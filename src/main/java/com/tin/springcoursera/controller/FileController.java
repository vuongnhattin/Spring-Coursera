package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.response.FileUploadResponse;
import com.tin.springcoursera.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload")
    public FileUploadResponse uploadFile(MultipartFile file) {
        String fileUrl = fileService.storeFile(file);
        return new FileUploadResponse(fileUrl);
    }

    @GetMapping("/file")
    public FileUploadResponse downloadFile(Authentication authentication, @RequestParam String objectKey) {
        URL fileUrl = fileService.getPresignedUrlIfAllowed(authentication, objectKey);
        return new FileUploadResponse(fileUrl.toString());
    }
}
