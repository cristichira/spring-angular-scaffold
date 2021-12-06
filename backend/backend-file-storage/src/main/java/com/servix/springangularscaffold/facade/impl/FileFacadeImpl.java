package com.servix.springangularscaffold.facade.impl;

import com.amazonaws.services.s3.model.S3Object;
import com.servix.springangularscaffold.dto.FileContentDto;
import com.servix.springangularscaffold.dto.FileDto;
import com.servix.springangularscaffold.dto.FileSaveDto;
import com.servix.springangularscaffold.dto.FileUploadDto;
import com.servix.springangularscaffold.exception.FileStorageException;
import com.servix.springangularscaffold.facade.FileFacade;
import com.servix.springangularscaffold.mapper.FileMapper;
import com.servix.springangularscaffold.service.FileProcessingService;
import com.servix.springangularscaffold.service.FileService;
import com.servix.springangularscaffold.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class FileFacadeImpl implements FileFacade {

    private final FileStorageService fileStorageService;
    private final FileProcessingService fileProcessingService;
    private final FileService fileService;
    private final FileMapper fileMapper;

    @Autowired
    public FileFacadeImpl(FileStorageService fileStorageService,
                          FileProcessingService fileProcessingService,
                          FileService fileService,
                          FileMapper fileMapper) {
        this.fileStorageService = fileStorageService;
        this.fileProcessingService = fileProcessingService;
        this.fileService = fileService;
        this.fileMapper = fileMapper;
    }

    @Override
    public FileContentDto findByStorageKey(String storageKey) {

        S3Object s3Object = fileStorageService.get(storageKey);
        final FileContentDto fileContentDto = new FileContentDto();
        fileContentDto.setContentType(s3Object.getObjectMetadata().getContentType());
        try {
            fileContentDto.setContent(s3Object.getObjectContent().readAllBytes());

            return fileContentDto;
        } catch (IOException e) {
            throw new FileStorageException("FILE_READ_EXCEPTION", e);
        }
    }

    @Override
    public FileDto upload(FileUploadDto fileUploadDto) {
        final FileSaveDto fileSaveDto = fileMapper.toFileSaveDto(fileUploadDto);
        fileProcessingService.process(fileSaveDto);
        fileStorageService.upload(fileSaveDto);

        return fileMapper.toFileDto(fileService.create(fileSaveDto));
    }
}
