package com.example.practice.project.controller;


import static com.example.practice.project.utilities.Constants.API;
import static com.example.practice.project.utilities.Constants.FILE;
import static com.example.practice.project.utilities.Constants.UPLOAD_MULTI_FILES;

import com.example.practice.project.dto.CompanyFileDto;
import com.example.practice.project.dto.FileDto;
import com.example.practice.project.entity.CompanyFiles;
import com.example.practice.project.model.request.MultipleFileRequest;
import com.example.practice.project.service.ICompanyService;
import com.example.practice.project.service.IFileService;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(API + FILE)
@CrossOrigin(originPatterns = "*", maxAge = 3600)
public class FileController {

    @Autowired
    private IFileService fileService;

    @Autowired
    private ICompanyService companyService;

    /**
     * Summary :: Upload File.
     *
     * @param file file
     * @return ResponseEntity
     */
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<FileDto> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        log.debug("Upload File Request");
        FileDto fileDto = fileService.uploadFile(file);
        log.debug("File Uploaded Successfully");
        return ResponseEntity.ok(fileDto);
    }

    @PostMapping(value = "upload-file")
    public ResponseEntity<CompanyFileDto> uploadCompanyFile(@RequestBody @Valid CompanyFileDto companyFileDto) {
        log.info("upload booking files: {}", companyFileDto);
        return ResponseEntity.ok(companyService.saveCompanyFiles(companyFileDto));
    }

    @PostMapping(value = UPLOAD_MULTI_FILES, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FileDto>> uploadMultiFiles(List<MultipartFile> files) throws IOException {
        log.info("Multi File Request: {}", files.size());
        return ResponseEntity.ok(fileService.uploadFiles(files));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileDto> getFile(@PathVariable Long id) {
        log.info("Get File By Id Request: {}", id);
        return ResponseEntity.ok(fileService.getFile(id));
    }

    @GetMapping("/company-file/{id}")
    public ResponseEntity<List<CompanyFileDto>> getCompanyFile(@PathVariable Long id) {
        log.info("Get File By Id Request: {}", id);
        return ResponseEntity.ok(companyService.getCompanyFiles(id));
    }

    /**
     * Summary :: Delete Files.
     *
     * @param multipleFileRequest multipleFileRequest
     * @return ResponseEntity
     */
    @DeleteMapping
    public ResponseEntity<String> deleteFiles(@RequestBody MultipleFileRequest multipleFileRequest) {
        log.info("Delete Files Request: {}", multipleFileRequest);
        fileService.deleteFiles(multipleFileRequest.getIds());
        return ResponseEntity.ok("Files Deleted");
    }
}
