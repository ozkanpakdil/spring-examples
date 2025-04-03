package com.example.demo.rest;

import com.example.demo.dto.PhotoDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Photo Api", description = "Фото")
public interface PhotoApi {
    @GetMapping("/photo/{path}")
    ResponseEntity<Resource> get(@PathVariable("path") String path);

    @PostMapping("/photo")
    ResponseEntity<PhotoDto> add(@RequestParam("file") MultipartFile file);
}
