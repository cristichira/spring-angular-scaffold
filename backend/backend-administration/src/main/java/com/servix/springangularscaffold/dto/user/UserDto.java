package com.servix.springangularscaffold.dto.user;

import com.servix.springangularscaffold.dto.AddressDto;
import com.servix.springangularscaffold.dto.base.BaseDto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class UserDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String fullName;
    private String urlName;
    private String companyName;
    private String email;
    private String phoneNumber;
    private String phoneNumberAlternate;
    private LocalDate birthDate;
    private Set<String> roles = new HashSet<>();
    private AddressDto address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberAlternate() {
        return phoneNumberAlternate;
    }

    public void setPhoneNumberAlternate(String phoneNumberAlternate) {
        this.phoneNumberAlternate = phoneNumberAlternate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
