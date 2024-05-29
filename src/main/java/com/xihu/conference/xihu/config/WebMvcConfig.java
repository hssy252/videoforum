package com.xihu.conference.xihu.config;


import com.xihu.conference.xihu.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginInterceptor)
            .excludePathPatterns("/achievement/**",
                "/activity/**",
                "/agendaContent/show/**",
                "/album/**",
                "/comment/add",
                "/goods/**",
                "images/show/**",
                "/integral/showAll",
                "/integral/showOne",
                "/integralRecord/show",
                "/integralRecord/show/**",
                "/news/**",
                "/post/show",
                "/topic/**",
                "/upload/**",
                "/verify/**",
                "/video/**")
            .addPathPatterns("/agenda/like",
                "/subscription/**",
                "/user/sign",
                "/user/userinfo",
                "/user/img");

    }

    /**
     * 通过knife4j生成接口文档
     *
     * @return
     */
    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
            .title("西湖论剑项目接口文档")
            .version("1.0")
            .description("西湖论剑项目接口文档")
            .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.xihu.conference.xihu.controller"))
            .paths(PathSelectors.any())
            .build();
        return docket;
    }

    /**
     * 设置静态资源映射
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
