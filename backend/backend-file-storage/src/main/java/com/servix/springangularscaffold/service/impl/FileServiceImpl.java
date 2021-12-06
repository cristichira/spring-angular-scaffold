package com.servix.springangularscaffold.service.impl;

import com.servix.springangularscaffold.domain.File;
import com.servix.springangularscaffold.dto.FileSaveDto;
import com.servix.springangularscaffold.mapper.FileMapper;
import com.servix.springangularscaffold.repository.FileRepository;
import com.servix.springangularscaffold.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository,
                           FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
    }

    @Override
    public File create(FileSaveDto fileSaveDto) {
        return fileRepository.save(fileMapper.toEntity(fileSaveDto));
    }
}
