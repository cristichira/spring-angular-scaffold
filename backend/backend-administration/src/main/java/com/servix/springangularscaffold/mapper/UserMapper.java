package com.servix.springangularscaffold.mapper;

import com.servix.springangularscaffold.dto.user.RegisterDto;
import com.servix.springangularscaffold.dto.user.UserDto;
import com.servix.springangularscaffold.dto.user.UserUpdateDto;
import com.servix.springangularscaffold.entity.User;
import com.servix.springangularscaffold.enumeration.UserRole;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Mapper(uses = AddressMapper.class)
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    public abstract UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "companyName", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "phoneNumberAlternate", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    public abstract User toEntityRegister(RegisterDto registerDto, String urlName);

    @Mapping(target = "urlName", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    public abstract void update(UserUpdateDto userUpdateDto, @MappingTarget User user);

    @AfterMapping
    void afterToRegisterMapping(RegisterDto registerDto, @MappingTarget User user) {
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(Collections.singleton(UserRole.ROLE_USER));
    }

    @AfterMapping
    void afterToEntity(@MappingTarget User user) {
        user.setFullName(String.format("%s %s", user.getFirstName(), user.getLastName()));
    }
}
