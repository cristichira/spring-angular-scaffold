package com.servix.springangularscaffold.mapper;

import com.servix.springangularscaffold.domain.File;
import com.servix.springangularscaffold.dto.FileDto;
import com.servix.springangularscaffold.dto.FileSaveDto;
import com.servix.springangularscaffold.dto.FileUploadDto;
import com.servix.springangularscaffold.exception.FileStorageException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Mapper
public abstract class FileMapper {

    @Value("${storage.aws.s3.public-files-prefix}")
    private String publicFilesPrefix;

    public abstract FileDto toFileDto(File file);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    public abstract File toEntity(FileSaveDto fileSaveDto);

    public FileSaveDto toFileSaveDto(FileUploadDto fileUploadDto) {
        try {
            final MultipartFile multipartFile = fileUploadDto.getMultipartFile();
            boolean isPublic = fileUploadDto.isPublic();
            final String storageFileName = computeStorageFileName(multipartFile, isPublic);

            final FileSaveDto fileSaveDto = new FileSaveDto();
            fileSaveDto.setName(multipartFile.getOriginalFilename());
            fileSaveDto.setStorageFileName(storageFileName);
            fileSaveDto.setContentType(multipartFile.getContentType());
            fileSaveDto.setSize(multipartFile.getSize());
            fileSaveDto.setContent(multipartFile.getInputStream().readAllBytes());
            fileSaveDto.setPublic(isPublic);

            return fileSaveDto;
        } catch (IOException e) {
            throw new FileStorageException("SAVE_FILE_EXCEPTION", e);
        }
    }

    private String computeStorageFileName(MultipartFile multipartFile, boolean isPublic) {
        final String prefix = isPublic ? publicFilesPrefix : Strings.EMPTY;
        final String fileName = multipartFile.getOriginalFilename();
        final String extension = StringUtils.contains(fileName, ".") ?
                "." + fileName.substring(fileName.lastIndexOf(".") + 1) :
                "";
        return prefix + "/" + UUID.randomUUID().toString() + extension;
    }
}
