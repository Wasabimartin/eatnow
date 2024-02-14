package com.eatnow.eatnow.services;

import com.eatnow.eatnow.dtos.FileDTO;
import com.eatnow.eatnow.model.File;
import com.eatnow.eatnow.model.FileModelMapper;
import com.eatnow.eatnow.model.Food;
import com.eatnow.eatnow.model.FoodModelMapper;
import com.eatnow.eatnow.repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public File uploadFile(MultipartFile file) throws IOException {
        return fileRepository.save(FileModelMapper.convertToEntity(file, null));
    }

    public File updateFile(Long fileId, MultipartFile file) throws IOException {
        File fileEntity = FileModelMapper.convertToEntity(file, fileId);
        fileRepository.save(fileEntity);
        return fileEntity;
    }

    public void deleteFile(Long fileId) {
        fileRepository.deleteById(fileId);
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public byte[] getFileById(Long fileId) throws IOException {
        Optional<File> fileOptional = fileRepository.findById(fileId);

        if (fileOptional.isPresent()) {
            File fileEntity = fileOptional.get();
            return fileEntity.getData();
        } else {
            throw new FileNotFoundException("File not found with id: " + fileId);
        }
    }

    public File getFileByIdValues(Long fileId) throws IOException {
        Optional<File> fileOptional = fileRepository.findById(fileId);

        if (fileOptional.isPresent()) {
            File fileEntity = fileOptional.get();
            return fileEntity;
        } else {
            throw new FileNotFoundException("File not found with id: " + fileId);
        }
    }

    public String getFileNameById(Long fileId) throws FileNotFoundException {
        Optional<File> fileOptional = fileRepository.findById(fileId);

        if (fileOptional.isPresent()) {
            File fileEntity = fileOptional.get();
            return fileEntity.getFileName();
        } else {
            throw new FileNotFoundException("File not found with id: " + fileId);
        }
    }


}
