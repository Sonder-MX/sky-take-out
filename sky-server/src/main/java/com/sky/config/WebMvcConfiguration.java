package com.sky.config;

import com.sky.interceptor.JwtTokenAdminInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");
    }

    /**
     * 通过knife4j生成接口文档
     * 
     * @return
     */
    // @Bean
    // Docket docket() {
    // log.info("开始生成接口文档...");
    // ApiInfo apiInfo = new ApiInfoBuilder()
    // .title("苍穹外卖项目接口文档")
    // .version("2.0")
    // .description("苍穹外卖项目接口文档")
    // .build();
    // Docket docket = new Docket(DocumentationType.SWAGGER_2)
    // .apiInfo(apiInfo)
    // .select()
    // .apis(RequestHandlerSelectors.basePackage("com.sky.controller"))
    // .paths(PathSelectors.any())
    // .build();
    // return docket;
    // }

    /**
     * 设置静态资源映射
     * 
     * @param registry
     */
    // protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    // log.info("开始设置静态资源映射...");
    // registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
    // registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }
}
