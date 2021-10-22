package com.servix.springangularscaffold.entity;

import com.servix.springangularscaffold.config.ProjectConstants;
import com.servix.springangularscaffold.entity.base.UrlFriendlyEntityObject;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = ProjectConstants.TABLE_PREFIX + "user",
        indexes = {@Index(name = "INDEX_" + ProjectConstants.TABLE_PREFIX + "user_url_name", columnList = "url_name", unique = true),
                @Index(name = "INDEX_" + ProjectConstants.TABLE_PREFIX + "user_email", columnList = "email", unique = true)}
)
public class User extends UrlFriendlyEntityObject {

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "full_name")
    private String fullName;

    @Size(max = 255)
    @Column(name = "company_name")
    private String companyName;

    @NotNull
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Size(max = 255)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Size(max = 255)
    @Column(name = "phone_number_alternate")
    private String phoneNumberAlternate;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ElementCollection
    @CollectionTable(name = ProjectConstants.TABLE_PREFIX + "user_role")
    private Set<String> roles;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", table = ProjectConstants.TABLE_PREFIX + "user")
    private Address address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberAlternate() {
        return phoneNumberAlternate;
    }

    public void setPhoneNumberAlternate(final String phoneNumberAlternate) {
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

    public void setRoles(final Set<String> roles) {
        this.roles = roles;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
