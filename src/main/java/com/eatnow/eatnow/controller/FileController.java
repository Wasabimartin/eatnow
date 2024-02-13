package com.eatnow.eatnow.controller;

import com.eatnow.eatnow.model.File;
import com.eatnow.eatnow.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        File uploadedFile = fileService.uploadFile(file);
        return ResponseEntity.ok(uploadedFile);
    }

    @PutMapping("/update/{fileId}")
    public ResponseEntity<File> updateFile(@PathVariable Long fileId, @RequestParam("file") MultipartFile file) throws IOException {
        File updatedFile = fileService.updateFile(fileId, file);
        return ResponseEntity.ok(updatedFile);
    }

    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable Long fileId) {
        fileService.deleteFile(fileId);
        return ResponseEntity.ok("File deleted successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<File>> getAllFiles() {
        List<File> allFiles = fileService.getAllFiles();
        return ResponseEntity.ok(allFiles);
    }
}
