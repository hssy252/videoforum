package com.xihu.conference.xihu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("西湖论剑api")
                .description("该文档用于提供后端各接口的信息")
                .version("1.0"));
    }

    @Bean
    public GroupedOpenApi customerApi() {
        return GroupedOpenApi.builder()
            .group("user")
            .pathsToMatch("/customer/**")
            .build();
    }

    @Bean
    public GroupedOpenApi agendaContentApi() {
        return GroupedOpenApi.builder()
            .group("agendaContent")
            .pathsToMatch("/agendaContent/**")
            .build();
    }

    @Bean
    public GroupedOpenApi agendaApi() {
        return GroupedOpenApi.builder()
            .group("agenda")
            .pathsToMatch("/agenda/**")
            .build();
    }

    @Bean
    public GroupedOpenApi uploadApi() {
        return GroupedOpenApi.builder()
            .group("upload")
            .pathsToMatch("/upload/**")
            .build();
    }

    @Bean
    public GroupedOpenApi goodsApi() {
        return GroupedOpenApi.builder()
            .group("goods")
            .pathsToMatch("/goods/**")
            .build();
    }

    @Bean
    public GroupedOpenApi achievementApi() {
        return GroupedOpenApi.builder()
            .group("achievement")
            .pathsToMatch("/achievement/**")
            .build();
    }

}