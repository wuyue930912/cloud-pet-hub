package com.pet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("大麦田")
                .description("大麦田のSwagger 3.0 , wuyue930912@live.com")
                .termsOfServiceUrl("https://blog.csdn.net/weixin_38045214?spm=1001.2014.3001.5343")
                .version("v1.0.0")
                .build();
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pet"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }
}
