package com.servix.springangularscaffold.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadDto {
    private MultipartFile multipartFile;
    private boolean isPublic;

    public FileUploadDto(MultipartFile multipartFile, boolean isPublic) {
        this.multipartFile = multipartFile;
        this.isPublic = isPublic;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
