package com.microserv.bbq.infrastructure.general.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 configuration
 *
 * @author jockeys
 * @since 2020/4/4
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String SWAGGER_SCAN_BASE_PACKAGE = "com.microserv.bbq.apis.controller";

        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.OAS_30)
                    .apiInfo(apiInfo())
                    .select()
//                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                    .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                    .paths(PathSelectors.any())
                    .build();
        }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ddd framework called bbq")
                .description("BBQ-DDD")
                .termsOfServiceUrl("http://bbq.microserv.com")
                .version("1.0.0")
                .build();
    }

}