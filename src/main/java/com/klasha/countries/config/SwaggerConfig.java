package com.klasha.countries.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title("Klasha Countries")
                        .description("Get info about countries")
                        .version("v.0.0.1")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}
