package com.example.practice.project.service;

import com.example.practice.project.dto.FileDto;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    FileDto uploadFile(MultipartFile file) throws IOException;

    List<FileDto> uploadFiles(List<MultipartFile> files) throws IOException;

    List<FileDto> getFiles(List<Long> ids);

    FileDto getFile(Long id);

    void deleteFiles(List<Long> ids);

}
