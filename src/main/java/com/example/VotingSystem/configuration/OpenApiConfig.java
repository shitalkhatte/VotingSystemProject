package com.example.VotingSystem.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig
{
    @Bean
    public OpenAPI userMicroserviceOpenAPI()
    {
        return new OpenAPI()
                .info(new Info().title("VOtingSystem")
                        .description("Voting System")
                        .version("1.0"));
    }
}
