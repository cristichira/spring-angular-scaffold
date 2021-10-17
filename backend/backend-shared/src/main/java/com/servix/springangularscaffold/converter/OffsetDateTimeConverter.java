package com.servix.springangularscaffold.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.OffsetDateTime;

@Converter
public class OffsetDateTimeConverter implements AttributeConverter<OffsetDateTime, String> {

    @Override
    public String convertToDatabaseColumn(OffsetDateTime attribute) {
        return attribute.toString();
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(String dbData) {
        return OffsetDateTime.parse(dbData);
    }
}
