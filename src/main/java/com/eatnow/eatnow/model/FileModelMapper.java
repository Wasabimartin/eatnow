package com.eatnow.eatnow.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileModelMapper {

    public static File convertToEntity(MultipartFile fileDTO) throws IOException {
//        System.out.println(fileDTO.getName());
        File file = new File();
        file.setFileName(fileDTO.getOriginalFilename());
        file.setFileType(fileDTO.getContentType());
        file.setData(fileDTO.getBytes());
        return file;
    }
}
