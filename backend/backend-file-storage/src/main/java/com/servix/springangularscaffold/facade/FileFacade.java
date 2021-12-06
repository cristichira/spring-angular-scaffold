package com.servix.springangularscaffold.facade;

import com.servix.springangularscaffold.dto.FileContentDto;
import com.servix.springangularscaffold.dto.FileDto;
import com.servix.springangularscaffold.dto.FileUploadDto;

public interface FileFacade {

    FileContentDto findByStorageKey(String storageKey);

    FileDto upload(FileUploadDto fileUploadDto);
}
