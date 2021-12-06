package com.servix.springangularscaffold.service;

import com.servix.springangularscaffold.dto.FileSaveDto;

public interface FileProcessingService {

    void process(FileSaveDto fileSaveDto);
}
