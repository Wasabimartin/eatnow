package com.eatnow.eatnow.controller;

import com.eatnow.eatnow.dtos.FileDTO;
import com.eatnow.eatnow.model.File;
import com.eatnow.eatnow.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("UPLOAD");
        try {
            File uploadedFile = fileService.uploadFile(file);
            return ResponseEntity.ok(uploadedFile);
        } finally {

        }
    }

//    @PostMapping("/upload")
//    public ResponseEntity<File> uploadFile(@RequestBody FileDTO fileDTO) throws IOException {
//        System.out.println("UPLOAD");
//        File file = new File();
//        file.setFileType(fileDTO.getFileType());
//        file.setFileName(fileDTO.getFileName());
//        file.setData(fileDTO.getData());
//        try {
//            File uploadedFile = fileService.uploadFile(file);
//            return ResponseEntity.ok(uploadedFile);
//        } finally {
//
//        }
//    }

    @PutMapping("/upload/{fileId}")
    public ResponseEntity<File> updateFile(@PathVariable Long fileId, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("UPDATE");
        try {
            File updatedFile = fileService.updateFile(fileId, file);
            return ResponseEntity.ok(updatedFile);
        }
        finally {

            }
        }

//    @PutMapping("/upload/{fileId}")
//    public ResponseEntity<File> updateFile(@PathVariable Long fileId, @RequestBody FileDTO fileDTO) throws IOException {
//        System.out.println("UPDATE");
//        try {
//            File updatedFile = fileService.updateFile(fileId, fileDTO);
//            return ResponseEntity.ok(updatedFile);
//        }
//        finally {
//
//        }
//    }

    @GetMapping("/upload/{fileId}")
    public ResponseEntity<Resource> getFile(@PathVariable Long fileId) throws IOException {
        byte[] fileContent = fileService.getFileById(fileId);
        if (fileContent == null) {
            return ResponseEntity.notFound().build();
        }
        System.out.println(fileContent);
        String fileName = fileService.getFileNameById(fileId);
        ByteArrayResource resource = new ByteArrayResource(fileContent);
        System.out.println(resource.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fileName) //
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(fileContent.length)
                .body(resource);
    }

    @GetMapping("/upload/fields/{fileId}")
    public File getFileValues(@PathVariable Long fileId) throws IOException {
        Optional<File> food = Optional.ofNullable(fileService.getFileByIdValues(fileId));
        return food.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)).getBody();

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

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws IOException {
        byte[] fileContent = fileService.getFileById(fileId);
        String fileName = fileService.getFileNameById(fileId);
        ByteArrayResource resource = new ByteArrayResource(fileContent);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(fileContent.length)
                .body(resource);
    }
}
