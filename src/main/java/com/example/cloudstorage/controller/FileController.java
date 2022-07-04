package com.example.cloudstorage.controller;

import com.example.cloudstorage.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class FileController {

    @Autowired
    FileStorageService fileService;

    @PostMapping("/upload/document")
    public Object upload(@RequestParam("file") MultipartFile multipartFile,
                         @RequestParam("type") String fileType) {
        log.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
        return fileService.upload(multipartFile,fileType);
    }

    @PutMapping("/update/documents")
    public ResponseEntity<?> updateDocuments(@RequestParam("id") int guarantor_id
            ,@RequestParam("file") MultipartFile file,@RequestParam("type") String fileType){
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/download/document/{fileName}")
    public Object download(@PathVariable String fileName) throws IOException {
        log.info("HIT -/download | File Name : {}", fileName);
        return fileService.download(fileName);
    }

}
