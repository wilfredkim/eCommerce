package com.ecommerce.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@EnableSwagger2
public class SwaggerConfig {

   /*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String LICENSE_TEXT = "License";
    private static final String title = " Restful Apis";
    private static final String description = "RESTFUL API for eCommerce System";
    private static final String termsOfUse = "---";
    private static final String licenceUrl = "---";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license(LICENSE_TEXT)
                .termsOfServiceUrl(termsOfUse)
                .licenseUrl(licenceUrl)
                .version(SWAGGER_API_VERSION)
                .build();
    }*/
}
