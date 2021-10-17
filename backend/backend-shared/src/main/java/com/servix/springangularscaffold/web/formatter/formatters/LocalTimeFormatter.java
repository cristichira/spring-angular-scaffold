package com.servix.springangularscaffold.web.formatter.formatters;

import org.springframework.format.Formatter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {

    @Override
    public LocalTime parse(String text, Locale locale) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        return LocalTime.from(dateTimeFormatter.parse(text));
    }

    @Override
    public String print(LocalTime object, Locale locale) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        return object.format(dateTimeFormatter);
    }
}
