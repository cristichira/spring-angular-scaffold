package com.servix.springangularscaffold.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;
import java.util.Objects;

@Converter
public class DurationConverter implements AttributeConverter<Duration, String> {

    @Override
    public String convertToDatabaseColumn(Duration attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }

        return attribute.toString();
    }

    @Override
    public Duration convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }

        return Duration.parse(dbData);
    }
}
