package com.servix.springangularscaffold.api.controller;

import com.servix.springangularscaffold.dto.FileContentDto;
import com.servix.springangularscaffold.dto.FileDto;
import com.servix.springangularscaffold.dto.FileUploadDto;
import com.servix.springangularscaffold.enumeration.UserRole;
import com.servix.springangularscaffold.facade.FileFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/files")
public class FileController {

    private final FileFacade fileFacade;

    @Autowired
    public FileController(FileFacade fileFacade) {
        this.fileFacade = fileFacade;
    }

    @GetMapping("/{storageKey}")
    public ResponseEntity<byte[]> getFileContent(@PathVariable("storageKey") String storageKey) {
        final FileContentDto fileContentDto = fileFacade.findByStorageKey(storageKey);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(fileContentDto.getContentType()));

        return new ResponseEntity<>(fileContentDto.getContent(), headers, HttpStatus.OK);
    }

    @PostMapping("/upload")
    @Secured(UserRole.ROLE_ADMIN)
    public ResponseEntity<FileDto> upload(@RequestParam("file") MultipartFile file,
                                          @RequestParam("isPublic") boolean isPublic) {
        final FileUploadDto fileUploadDto = new FileUploadDto(file, isPublic);
        return ResponseEntity.ok(fileFacade.upload(fileUploadDto));
    }
}
