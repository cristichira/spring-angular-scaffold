package com.servix.springangularscaffold.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;

//TODO: change with final and correct info
@OpenAPIDefinition(
        info = @Info(
                title = "Servix Api",
                description = "Service Platform OpenApi Specification",
                version = "1.0.0",
                contact = @Contact(
                        name = "Cristian Chira",
                        url = "https://servix.ro",
                        email = "cristichira1@gmail.com"
                ),
                license = @License(
                        name = "Attribution-NonCommercial-NoDerivatives 4.0 International",
                        url = "https://servix.ro")),
        security = {@SecurityRequirement(name = "jwt")}
)
@Configuration
public class OpenAPIConfig {
}
