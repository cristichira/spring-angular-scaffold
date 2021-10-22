package com.servix.springangularscaffold.entity;

import com.servix.springangularscaffold.config.ProjectConstants;
import com.servix.springangularscaffold.entity.base.EntityObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;


@Entity
@Table(name = ProjectConstants.TABLE_PREFIX + "address")
public class Address extends EntityObject {

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "country")
    private String country;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "longitude", precision = 7, scale = 3)
    private BigDecimal longitude;

    @Column(name = "latitude", precision = 7, scale = 3)
    private BigDecimal latitude;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
