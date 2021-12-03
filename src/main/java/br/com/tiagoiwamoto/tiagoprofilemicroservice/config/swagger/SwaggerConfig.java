package br.com.tiagoiwamoto.tiagoprofilemicroservice.config.swagger;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 28/04/2021 | 21:35
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public final static String TITLE = "Clean Arch API";
    public final static String DESCRIPTION = "Example of Clean Archite";
    public final static String VERSION = "1.0.0";
    public final static String LICENSE_URL = "https://choosealicense.com/licenses/gpl-3.0/";
    public final static String CONTACT_NAME = "Tiago";
    public final static String CONTACT_URL = "https://tiagoiwamoto.github.io";
    public final static String CONTACT_EMAIL = "tiago.iwamoto@gmail.com";

    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build().apiInfo(this.buildApiInfo());
    }

    private ApiInfo buildApiInfo(){
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .licenseUrl(LICENSE_URL)
                .contact(new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL))
                .build();
    }
}
