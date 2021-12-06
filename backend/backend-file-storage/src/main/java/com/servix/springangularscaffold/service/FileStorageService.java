package com.servix.springangularscaffold.service;

import com.amazonaws.services.s3.model.S3Object;
import com.servix.springangularscaffold.dto.FileSaveDto;

public interface FileStorageService {

    S3Object get(String key);

    void upload(FileSaveDto fileSaveDto);

    void delete(String key);
}
