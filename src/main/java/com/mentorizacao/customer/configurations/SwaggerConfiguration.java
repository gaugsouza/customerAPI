package com.mentorizacao.customer.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * © Copyright Beta IT 2019<br>
 *
 * The <code>SwaggerConfig</code> is about Swagger configuration and enabling this configuration into this application.
 *
 * @author gaugusto@betait.com.br
 * @version 1.0
 * @since JDK1.8
 *
 * @see org.springframework.context.annotation.Bean;
 * @see org.springframework.context.annotation.Configuration;
 * @see RequestHandlerSelectors;
 * @see ApiInfo;
 * @see Contact;
 * @see DocumentationType;
 * @see Docket;
 * @see EnableSwagger2;
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    /**
     * Mapping all APIs and enabling it into this application.
     *
     * @return Docket
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mentorizacao.customer.controllers"))
                .paths(regex("/*.*"))
                .build()
                .apiInfo(metaData());
    }

    /**
     * Metadata regarding all APIs.
     *
     * @return ApiInfo
     */
    private ApiInfo metaData() {
        return  new ApiInfo(
                "Customer REST API",
                "Customer REST API for Beta IT",
                "1.0.0",
                "Terms of service",
                new Contact("Guilherme Augusto de Souza"
                        , "http://dev.betait.com.br/gaugusto"
                        , "gaugusto@betait.com.br"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
    }

}
