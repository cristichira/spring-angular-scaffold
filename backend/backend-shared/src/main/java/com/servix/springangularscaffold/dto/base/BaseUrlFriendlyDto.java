package com.servix.springangularscaffold.dto.base;

public abstract class BaseUrlFriendlyDto extends BaseDto {
    private String urlName;

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }
}
