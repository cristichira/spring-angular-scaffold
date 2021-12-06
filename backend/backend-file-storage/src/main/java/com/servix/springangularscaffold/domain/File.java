package com.servix.springangularscaffold.domain;

import com.servix.springangularscaffold.config.ProjectConstants;
import com.servix.springangularscaffold.entity.base.EntityObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = ProjectConstants.TABLE_PREFIX + "file",
        uniqueConstraints = @UniqueConstraint(name = "UK_cr_file_storage_file_name", columnNames = {"storage_file_name"})
)
public class File extends EntityObject {

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "storage_file_name")
    private String storageFileName;

    @NotBlank
    @Column(name = "content_type")
    private String contentType;

    @Positive
    @Column(name = "size")
    private long size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStorageFileName() {
        return storageFileName;
    }

    public void setStorageFileName(String storageFileName) {
        this.storageFileName = storageFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
