package com.clock.time.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "Speaking Clock APIS", version = "1.0", description = "API for accessing 24-hour clock related information"))
public class OpenApiConfig {
    @Bean
    public OpenAPI openApiInformation() {
        Contact contact = new Contact()
                .email("gitishbhusri@gmail.com")
                .name("Gitish Bhusri");

        Info info = new Info()
                .contact(contact)
                .description("An API for accessing 24-hour clock information.")
                .title("Speaking Clock API")
                .version("V1.0.0");

        return new OpenAPI().info(info);
    }
}
