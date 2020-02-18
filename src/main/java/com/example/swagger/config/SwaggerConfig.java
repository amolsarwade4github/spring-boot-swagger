package com.example.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .paths(PathSelectors.ant("/api/**"))
                    .apis(RequestHandlerSelectors.basePackage("com.example.swagger"))
                    .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Address Book API",
                "Sample APIs for POC",
                "1.0",
                "Open source",
                new Contact(
                        "Amol Sarwade",
                        "https://github.com/amolsarwade4github",
                        "amol.sarwade91@gmail.com"),
                "API License",
                "https://github.com/amolsarwade4github/spring-boot-swagger",
                Collections.emptyList()
        );
    }

}
