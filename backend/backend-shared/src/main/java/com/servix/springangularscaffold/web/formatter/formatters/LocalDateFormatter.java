package com.servix.springangularscaffold.web.formatter.formatters;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return LocalDate.from(dateTimeFormatter.parse(text));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return object.format(dateTimeFormatter);
    }
}
