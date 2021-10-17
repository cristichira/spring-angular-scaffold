package com.servix.springangularscaffold.web.formatter;

import com.servix.springangularscaffold.web.formatter.formatters.LocalDateFormatter;
import com.servix.springangularscaffold.web.formatter.formatters.LocalTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FormatterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(localDateFormatter());
        registry.addFormatter(localTimeFormatter());
    }

    @Bean
    public LocalTimeFormatter localTimeFormatter() {
        return new LocalTimeFormatter();
    }

    @Bean
    public LocalDateFormatter localDateFormatter() {
        return new LocalDateFormatter();
    }
}
