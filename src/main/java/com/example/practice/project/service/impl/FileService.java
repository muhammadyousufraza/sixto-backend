package com.example.practice.project.service.impl;

import static com.example.practice.project.utilities.Constants.FILE_NOT_FOUND;

import com.example.practice.project.customexception.NotFoundException;
import com.example.practice.project.dto.FileDto;
import com.example.practice.project.entity.File;
import com.example.practice.project.repository.FileRepository;
import com.example.practice.project.service.IFileService;
import com.example.practice.project.utilities.ModelConverter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class FileService implements IFileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileDto uploadFile(MultipartFile fileObj) throws IOException {
        log.info("Uploading file: {}", fileObj.getOriginalFilename());
        FileDto fileDto = new FileDto();
        fileDto.setImage(fileObj.getBytes());
        fileDto.setName(fileObj.getOriginalFilename());
        File file = ModelConverter.convertToEntity(fileDto);
        file.setCreatedDate(LocalDateTime.now());
        file = fileRepository.save(file);
        log.info("File uploaded successfully: {}", file.getName());
        return ModelConverter.convertToDto(file);
    }

    @Override
    public List<FileDto> uploadFiles(List<MultipartFile> files) throws IOException {
        log.info("Uploading {} files", files.size());
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            File fileObj = new File();
            fileObj.setImage(file.getBytes());
            fileObj.setName(file.getOriginalFilename());
            fileObj.setCreatedDate(LocalDateTime.now());
            fileList.add(fileObj);
        }
        List<File> savedFiles = fileRepository.saveAll(fileList);

        return ModelConverter.convertToFileDtosList(savedFiles);
    }

    @Override
    public List<FileDto> getFiles(List<Long> ids) {
        log.info("Getting files with ID: {}", ids);
        List<FileDto> fileDtos = new ArrayList<>();
        List<File> files = fileRepository.findAllById(ids);
        if (!files.isEmpty()) {
            fileDtos = ModelConverter.convertToFileDtosList(files);
        }
        return fileDtos;
    }

    @Override
    public FileDto getFile(Long id) {
        log.info("Getting file with ID: {}", id);
        Optional<File> fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()) {
            File file = fileOptional.get();
            log.info("FileId: {}", file.getId());
            return ModelConverter.convertToDto(file);
        } else {
            log.error("File not found with Parameters: {}", id);
            throw new NotFoundException(FILE_NOT_FOUND);
        }
    }

    @Override
    public void deleteFiles(List<Long> ids) {
        log.info("Into delete files");
        fileRepository.deleteAllById(ids);
    }
}
