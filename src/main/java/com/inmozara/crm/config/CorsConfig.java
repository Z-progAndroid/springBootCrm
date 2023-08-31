package com.inmozara.crm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*") // Permitir cualquier encabezado
                .allowCredentials(true)
                .exposedHeaders(
                        HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN
                        , HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS
                        , HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS
                        , HttpHeaders.ACCESS_CONTROL_MAX_AGE
                        , HttpHeaders.AUTHORIZATION)
                .maxAge(3600);
    }
}