package com.servix.springangularscaffold.service;

import com.servix.springangularscaffold.domain.File;
import com.servix.springangularscaffold.dto.FileSaveDto;

public interface FileService {

    File create(FileSaveDto fileSaveDto);
}
