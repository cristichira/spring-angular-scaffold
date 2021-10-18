package com.servix.springangularscaffold.dto.user;

import com.servix.springangularscaffold.dto.AddressDto;

import java.time.LocalDate;

public class UserUpdateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String phoneNumber;
    private String phoneNumberAlternate;
    private LocalDate birthDate;
    private AddressDto address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
