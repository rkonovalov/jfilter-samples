package jfilter.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()).
        tags(new Tag("dynamic-filter", "Dynamic filter"),
                new Tag("dynamic-session-filter", "Dynamic session filter"),
                new Tag("full-controller-filter", "Full controller filter"),
                new Tag("session-controller-filter", "Session controller filter"),
                new Tag("simple-filter", "Simple filter"),
                new Tag("xml-configured-filter", "Xml schema-based filter")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("JFIlter Samples", "", "1.0.15", "",
                new Contact("Ruslan Konovalov", "", "rkonovalov86@gmail.com"), "Apache 2.0", "", Collections.emptyList());
    }

}