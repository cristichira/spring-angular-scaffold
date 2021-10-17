package com.servix.springangularscaffold.entity.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class UrlFriendlyEntityObject extends EntityObject {

    @NotNull
    @Size(min = 1, max = 255)
    @Pattern(regexp = "([A-Za-z0-9_.\\-]+)")
    @Column(name = "url_name")
    private String urlName;

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }
}
