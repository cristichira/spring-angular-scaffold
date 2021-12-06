package com.servix.springangularscaffold.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.servix.springangularscaffold.dto.FileSaveDto;
import com.servix.springangularscaffold.exception.FileStorageException;
import com.servix.springangularscaffold.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final AmazonS3 amazonS3;

    @Value("${storage.aws.s3.bucket-name}")
    private String bucketName;

    @Autowired
    public FileStorageServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public S3Object get(String key) {
        if (!amazonS3.doesObjectExist(bucketName, key)) {
            throw new FileStorageException("FILE_NOT_FOUND");
        }
//        TODO: fix public permissions
//        if (!amazonS3.getObjectAcl(bucketName, key).getGrantsAsList().contains(CannedAccessControlList.PublicRead)) {
//            throw new FileStorageException("NO_PUBLIC_PERMISSION");
//        }

        return amazonS3.getObject(bucketName, key);
    }

    @Override
    public void upload(FileSaveDto fileSaveDto) {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(fileSaveDto.getContentType());
        objectMetadata.setContentLength(fileSaveDto.getSize());

        final InputStream inputStream = new ByteArrayInputStream(fileSaveDto.getContent());
        final String storageFileName = fileSaveDto.getStorageFileName();
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, storageFileName, inputStream, objectMetadata);

//        TODO: fix public permissions
//        if (fileSaveDto.isPublic()) {
//            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
//        }

        amazonS3.putObject(putObjectRequest);
    }

    @Override
    public void delete(String key) {
        amazonS3.deleteObject(bucketName, key);
    }


}
