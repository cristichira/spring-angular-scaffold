package com.servix.springangularscaffold.mapper;

import com.servix.springangularscaffold.dto.AddressDto;
import com.servix.springangularscaffold.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface AddressMapper {

    AddressDto toDto(Address address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    void toEntityUpdate(AddressDto addressDto, @MappingTarget Address address);

    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    Address toEntity(AddressDto addressDto);
}
