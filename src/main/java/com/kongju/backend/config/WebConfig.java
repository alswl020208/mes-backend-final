package com.kongju.backend.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//addMapping("/**")	모든 경로에 대해 CORS 설정을 적용합니다.
//allowedOrigins("*")	모든 오리진(출처)에서의 요청을 허용합니다.
//allowedMethods("*")	허용되는 HTTP 메서드를 지정합니다.
//allowedHeaders("*")	모든 헤더를 허용합니다.
//allowCredentials(true)	인증된 요청을 허용합니다 (예: 쿠키, HTTP 인증).

@Configuration

public class WebConfig implements WebMvcConfigurer {

    // CORS 오류 조치
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
