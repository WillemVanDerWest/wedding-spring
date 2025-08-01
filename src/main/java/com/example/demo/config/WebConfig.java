package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Value("${custom.cors}")
    String cors;

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        System.out.println(cors);
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(cors, "http://localhost:3000", "http://192.168.1.97:3000", "https://cabousenjeanine.co.za", "https://cabousenjeanine.co.za", "https://cabousenjeanine.co.za/admin")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true);
            }
        };
    }
}
