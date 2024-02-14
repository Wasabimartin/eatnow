package com.eatnow.eatnow.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileModelMapper {

    public static File convertToEntity(MultipartFile fileDTO, Long id) throws IOException {
        File file = new File();
        file.setId(id);
        file.setFileName(fileDTO.getOriginalFilename());
        file.setFileType(fileDTO.getContentType());
        file.setData(fileDTO.getBytes());
        return file;
    }
}
