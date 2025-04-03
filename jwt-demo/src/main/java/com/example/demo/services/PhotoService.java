package com.example.demo.services;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    String upload(MultipartFile file);

    byte[] download(String fileName);

    void delete(String fileName);

    void checkIfExists(String fileName);
}
