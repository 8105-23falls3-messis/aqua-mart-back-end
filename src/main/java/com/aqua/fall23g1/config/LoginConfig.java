package com.aqua.fall23g1.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aqua.fall23g1.interceptor.UserLoginInterceptor;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    private UserLoginInterceptor userLoginInterceptor;

    public LoginConfig(UserLoginInterceptor userLoginInterceptor) {
        this.userLoginInterceptor = userLoginInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/user/**");
        excludePath.add("/error");
        excludePath.add("/swagger-ui/**");
        excludePath.add("/swagger-resources/**");
        excludePath.add("/swagger-ui.html");
        excludePath.add("/v2/api-docs");
        excludePath.add("/webjars/**");
        // excludePath.add("/user/login");
        excludePath.add("/img/**");
        excludePath.add("/css/**");
        excludePath.add("/static/**");
        excludePath.add("/dist/**");
        excludePath.add("/images/**");
        excludePath.add("/components/**");
        excludePath.add("/public/**");
        excludePath.add("/*.js");
        excludePath.add("/*.css");
        excludePath.add("/*.json");
        excludePath.add("/*.icon");
        excludePath.add("/*.ico");
        excludePath.add("/*.txt");
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
