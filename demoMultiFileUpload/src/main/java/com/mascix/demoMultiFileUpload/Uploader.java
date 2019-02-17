package com.mascix.demoMultiFileUpload;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class Uploader {
    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("file") MultipartFile[] files,
                              @RequestParam("extradata") String[] extras) throws Exception {
        int i = 0;
        Files.createDirectories(Paths.get("./uploaded/"));
        for (MultipartFile f : files) {
            Files.copy(f.getInputStream(), Paths.get("./uploaded/" + extras[i] + f.getOriginalFilename()));
            i++;
        }
        return "success";
    }
}
