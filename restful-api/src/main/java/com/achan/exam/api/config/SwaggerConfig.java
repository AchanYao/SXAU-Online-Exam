package com.achan.exam.api.config;

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
 * @author Achan
 * @date 2020/1/16
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket adminApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin")
                .apiInfo(adminApiInfo("管理端"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.achan.exam.admin"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket questionBank() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("question-bank")
                .apiInfo(adminApiInfo("题库"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.achan.exam.qbank"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket authServer() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("auth-server")
                .apiInfo(adminApiInfo("oauth2授权服务器"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket authClient() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("auth-client")
                .apiInfo(adminApiInfo("oauth2资源服务器"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.achan.exam.auth.client"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo adminApiInfo(String description) {
        return new ApiInfoBuilder()
                .title("山西农业大学软件学院阶段性考核系统-" + description)
                .version("1.0")
                .build();
    }
}
